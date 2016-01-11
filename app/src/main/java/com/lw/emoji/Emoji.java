package com.lw.emoji;

/*
 * 存储表情相关信息。 
 * 重写 equals当表情名称相同时表示两个表情是同一个. 
 */
public class Emoji {
	

	/**  表情名称   */ 
	public			String		name;
	
	public			int			id;
	
	
	public	static 	Emoji	create(String name , int id){
		Emoji	e = new Emoji();
		e.name = name;
		e.id = id;
		return e;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emoji other = (Emoji) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return EmojiManager.emojiChars(this);
	}
}
