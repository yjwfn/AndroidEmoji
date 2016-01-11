package com.lw.emoji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.TextUtils;


/**
 * 管理emoji
 * 搜索处理文体将表情文体替换为表情。 
 * @author yjwfn
 *
 */
public class EmojiManager {

	/** 表情map  */
	private		Map<String , Emoji>			eMap;

	/** emoji匹配模式   [emoji]  */
	private		Pattern						ePattern = Pattern.compile("\\[(\\w+)\\]");
	
	
	private		static		 	EmojiManager		INSTANCE = null;
	
	private		EmojiManager(){
		
		eMap  = new HashMap<>();
	}
	
	/**
	 * 生成emoji字符
	 */
	public static 		String	emojiChars(Emoji e){
		
			StringBuffer  sb = new StringBuffer();
			sb.append("[");
			sb.append(e.name);
			sb.append("]");
			
			return sb.toString();
	}
	
	
	public		Class<?>	getWhatClass(){
		
		return EmojiDrawableSpan.class;
	}
	
	/**
	 * 提取表情信息，制作字符集。  
	 */
	public		void	addEmoji(IEmojiPackage emojiPackage){
			
				for(int j = 0; j < emojiPackage.size(); j++)
						eMap.put(emojiPackage.get(j).name  , emojiPackage.get(j));
	}
	
	/**
	 * 根据指定的规则搜索emoji字符
	 * 
	 * @param text
	 * @return
	 */
	public		List<EmojiMeta> 	searchEmoji(Context context, CharSequence text){
			
				if(TextUtils.isEmpty(text))
					return null;
				
				List<EmojiMeta>		eMeta = null;
				Matcher	matcher = ePattern.matcher(text);
				
				while(matcher.find()){
					if(eMeta == null)
						eMeta = new ArrayList<>();
					
					EmojiMeta em = new EmojiMeta();
					em.name = matcher.group(1);
					em.start = matcher.start();
					em.end = matcher.end();
					
					Emoji  emoji = eMap.get(em.name);
					
					if(emoji !=null){
						EmojiDrawableSpan dspan = new EmojiDrawableSpan(context, emoji);
						em.what = dspan;
						eMeta.add(em);
					}
					
				}
		
			 return eMeta;
	}
	
	/** 封装搜索到的信息  */
	public	class 	EmojiMeta {
		public	String 	name;
		public	int		start;
		public	int		end;
		public	Object	what;
	}
	
	 

	public synchronized static EmojiManager getInstance() {
	 
		if(INSTANCE == null)
			INSTANCE  = new EmojiManager();
		
		return INSTANCE;
	}
}
