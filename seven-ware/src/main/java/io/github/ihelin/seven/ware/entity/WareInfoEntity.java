package io.github.ihelin.seven.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


/**
 * ²Ö¿âÐÅÏ¢
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:18:39
 */

@TableName("wms_ware_info")
public class WareInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ²Ö¿âÃû
	 */
	private String name;
	/**
	 * ²Ö¿âµØÖ·
	 */
	private String address;
	/**
	 * ÇøÓò±àÂë
	 */
	private String areacode;

}
