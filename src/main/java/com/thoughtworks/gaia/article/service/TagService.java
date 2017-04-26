package com.thoughtworks.gaia.article.service;

import com.thoughtworks.gaia.article.entity.Tag;
import com.thoughtworks.gaia.article.model.TagModel;
import com.thoughtworks.gaia.article.repository.TagRepository;
import com.thoughtworks.gaia.product.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class TagService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private TagRepository tagRepository;

    public List<TagModel> createIfNotExist(List<String> tags) {
        if (CollectionUtils.isEmpty(tags)) {
            return null;
        }
        List<TagModel> existTags = tagRepository.findAllByContentIn(tags);
        List<String> notExistTagContent = new ArrayList<>(tags);

        notExistTagContent.removeAll(existTags.stream().map(TagModel::getContent).collect(Collectors.toList()));
        List<TagModel> createdTags = createTags(notExistTagContent);

        existTags.addAll(createdTags);
        return existTags;
    }

    private List<TagModel> createTags(List<String> tags) {
        if (CollectionUtils.isEmpty(tags)) {
            return new ArrayList<>();
        }
        return tagRepository.save(tags.stream().map(TagModel::new).collect(Collectors.toList()));
    }

    public Tag get(Long tagId) {
        TagModel tagModel = tagRepository.findOne(tagId);
        if (tagModel == null) {
            return null;
        }
        tagModel.getArticles();
        return commonMapper.map(tagModel, Tag.class);
    }
}
