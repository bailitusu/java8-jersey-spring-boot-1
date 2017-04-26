package com.thoughtworks.gaia.common;

public abstract class ObjectMapper<MDL, DTO> extends MapperBase {

    public abstract DTO fromModel(MDL model);

    public abstract MDL fromDTO(DTO dto);
}
