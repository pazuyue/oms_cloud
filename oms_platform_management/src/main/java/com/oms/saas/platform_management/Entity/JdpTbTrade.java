package com.oms.saas.platform_management.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 月光光
 * @since 2023-05-31
 */
@Data
@TableName("jdp_tb_trade")
public class JdpTbTrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("tid")
    private Long tid;
    @TableField("status")
    private String status;
    @TableField("type")
    private String type;
    @TableField("seller_nick")
    private String sellerNick;
    @TableField("buyer_nick")
    private String buyerNick;
    @TableField("created")
    private Date created;
    @TableField("modified")
    private Date modified;
    @TableField("jdp_hashcode")
    private String jdpHashcode;
    @TableField("jdp_response")
    private String jdpResponse;
    @TableField("jdp_created")
    private Date jdpCreated;
    @TableField("jdp_modified")
    private Date jdpModified;
}
