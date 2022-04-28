package com.xixi.ddms.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by zhengyuxi
 * @description: com.xixi.ddms.modal
 * @date:2022/4/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceBean {
    private Integer deviceId;
    private String deviceName;
    private boolean fixing;
    private boolean scrapped;
    private boolean used;
    private Integer uuid;
}
