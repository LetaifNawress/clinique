package com.example.clinique.DTO.BilanCreation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = GlycemieDTO.class, name = "GLYCEMIE"),
    @JsonSubTypes.Type(value = HemoglobineDTO.class, name = "HEMOGLOBINE"),
    @JsonSubTypes.Type(value = LipidiqueDTO.class, name = "LIPIDIQUE"),
    @JsonSubTypes.Type(value = TransaminaseDTO.class, name = "TRANSAMINASE"),
        @JsonSubTypes.Type(value = IonogrammeSanguinDTO.class, name = "IONOGRAMME_SANGUIN")
})
public class AnalyseDTO {
    public AnalyseType type;
}
