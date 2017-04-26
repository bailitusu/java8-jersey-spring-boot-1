package com.thoughtworks.gaia.common;

import com.thoughtworks.gaia.article.entity.Article;
import com.thoughtworks.gaia.article.model.ArticleModel;

import java.util.List;

public abstract class ObjectMapper<MDL, DTO> extends MapperBase {

    public abstract DTO fromModel(MDL model);

    public abstract MDL fromDTO(DTO dto);

    public abstract List<MDL> fromDTOList(List<DTO> list);

    public abstract List<DTO> fromModelList(List<MDL> list);
}
