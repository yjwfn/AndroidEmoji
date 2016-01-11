package com.lw.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.style.CharacterStyle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lw.R;
import com.lw.emoji.Emoji;
import com.lw.emoji.IEmojiPackage;

public class 	EmojiAdapter extends BaseAdapter{
	
	private 	IEmojiPackage emojiPackage;
	
	private		Context				context;

	public EmojiAdapter(Context context, IEmojiPackage factor) {
		super();
		this.emojiPackage = factor;
		this.context  =context;
	}

	@Override
	public int getCount() {
		return emojiPackage.size();
	}

	@Override
	public Object getItem(int position) {
		return emojiPackage.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		if(convertView == null) 
				convertView = LayoutInflater.from(context).inflate(R.layout.emoji_item, parent, false);
		 
		
		TextView tv = (TextView) convertView.findViewById(R.id.emoji_text_view);
		Emoji emoji = emojiPackage.get(position);
		tv.setText(  emoji.toString() );

		
		return convertView;
	}
	
}