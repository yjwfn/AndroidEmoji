package com.lw.emoji;

import android.util.Log;

import com.lw.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AndroidEmojiPackage implements IEmojiPackage {

	private static final String TAG = "AndroidEmojiPackage";

	private List<Emoji> mEmojiList;


	public AndroidEmojiPackage(){
		mEmojiList = new ArrayList<>();
		loadEmoji();
	}
	/**
	 * 加载表情符号。
	 * 反射R文件。
	 */
	protected		void	loadEmoji(){
		try{
			Class  	rDrawable = R.mipmap.class;
			Field[]	fields = rDrawable.getFields();

			for(Field f: fields){

				String name = f.getName();

				if(!isEmoji(name))
					continue ;

				int id =  f.getInt(null);
				Emoji e = Emoji.create(name,  id);

				mEmojiList.add(e);
			}
		}catch(Exception e){
			Log.w(TAG, e.getMessage());
			e.printStackTrace();
		}
	}


	protected boolean isEmoji(String name) {
		
		if(name.startsWith("emoji")  )
				return true;
		
		return false;
	}

	@Override
	public Emoji get(int index) {
		return mEmojiList.size() > index ? mEmojiList.get(index) : null;
	}

	@Override
	public int size() {
		return mEmojiList.size();
	}
}
