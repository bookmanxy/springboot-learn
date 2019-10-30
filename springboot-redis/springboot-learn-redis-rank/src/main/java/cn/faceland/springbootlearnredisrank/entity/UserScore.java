package cn.faceland.springbootlearnredisrank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author watermelon on 2019/10/30 15:55
 * @description 用户排名类
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserScore {
    /**
     * 排名
     */
    private Long rank;
    /**
     * 分数
     */
    private Double score;
}
