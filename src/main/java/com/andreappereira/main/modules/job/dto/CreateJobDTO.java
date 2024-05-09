package com.andreappereira.main.modules.job.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateJobDTO {
    @Schema(example = "React Native Developer")
    private String description;

    @Schema(example = "Health insurance, Paid parental leave, Parental leave")
    private String benefits;
}
