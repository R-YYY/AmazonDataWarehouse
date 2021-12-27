package com.DataWarehouse.sys.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LIAN
 * @since 2021-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    private String movieId;

    private String title;

    private Integer directorNum;

    private Integer actorNum;

    private Double score;

    private String format;

    private String type;

    private String year;

    private String month;

    private String day;

    private Integer reviewNum;


}
