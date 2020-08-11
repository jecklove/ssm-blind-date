package org.jecklove.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Region {
    private Long id;

    private Long parentId;

    private Long rootId;

    private String name;

    private String fullName;

    private Byte level;

}