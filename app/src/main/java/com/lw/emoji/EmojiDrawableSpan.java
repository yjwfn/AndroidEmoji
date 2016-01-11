package com.lw.emoji;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout.Alignment;
import android.text.style.AlignmentSpan;
import android.text.style.DynamicDrawableSpan;

public class EmojiDrawableSpan extends DynamicDrawableSpan {

	private		Context		mContext;
	
	private		Emoji		emoji;

	public EmojiDrawableSpan(Context mContext, Emoji emoji) {
		super(ALIGN_BOTTOM);
		
		this.mContext = mContext;
		this.emoji = emoji;
	}

	@Override
	public Drawable getDrawable() {
		 
		Drawable draw = mContext.getResources().getDrawable(emoji.id);
		draw.setBounds(0, 0, draw.getMinimumWidth(), draw.getMinimumHeight());
		
		return draw;
	}
}
