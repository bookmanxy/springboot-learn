package cn.faceland.springbootlearnredisgeo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户信息</h1>
 * Created by Qinyi.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    /** 姓名 */
    private String name;

    /** 经度 */
    private Double longitude;

    /** 纬度 */
    private Double latitude;
}
