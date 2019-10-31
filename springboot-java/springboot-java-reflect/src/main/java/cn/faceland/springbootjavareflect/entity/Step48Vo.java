package cn.faceland.springbootjavareflect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author watermelon on 2019/10/31 10:32
 * @description
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Step48Vo {
    private Integer hourStepNumber;
}
