package common.model.entity;

/** 响应类型枚举 */
public enum ResponseType {
	/** 文本内容 */
	TEXT,
	/** 准备发送文件 */
	TOSENDFILE,
	/** 同意接收文件 */
	AGREERECEIVEFILE,
	/** 拒绝接收文件 */
	REFUSERECEIVEFILE,
	/** 发送文件 */
	RECEIVEFILE,
	/** 客户端登录 */
	LOGIN,
	/** 客户端退出 */
	LOGOUT,
	/** 聊天 */
	CHAT,
	/** 振动 */
	SHAKE,
	/** 其它 */
	OTHER,
	/** 管理员踢出对方*/
	LOGYOU
}
