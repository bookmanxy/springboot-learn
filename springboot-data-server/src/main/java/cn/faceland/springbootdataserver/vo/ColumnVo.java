package cn.faceland.springbootdataserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author watermelon
 * @Date 2019-11-28
 * @Description
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ColumnVo {
    private String name;
    private String value;
    private String type;
}
