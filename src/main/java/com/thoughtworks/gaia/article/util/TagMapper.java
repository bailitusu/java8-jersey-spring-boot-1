package com.thoughtworks.gaia.article.util;

import com.thoughtworks.gaia.article.entity.Tag;
import com.thoughtworks.gaia.article.model.TagModel;
import com.thoughtworks.gaia.common.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagMapper extends ObjectMapper<TagModel, Tag> {

    @Autowired
    private ArticleMapper articleMapper;

    public TagModel map(Tag tag) {
        return this.map(tag, TagModel.class);
    }

    @Override
    public Tag fromModel(TagModel model) {
        Tag tag = this.map(model, Tag.class);
        return null;
    }

    @Override
    public TagModel fromDTO(Tag tag) {
        return null;
    }

    @Override
    public List<TagModel> fromDTOList(List<Tag> list) {
        return null;
    }

    @Override
    public List<Tag> fromModelList(List<TagModel> list) {
        return null;
    }
}
