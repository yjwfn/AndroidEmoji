package com.lw.view;

import java.util.List;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.EditText;

import com.lw.emoji.EmojiManager;


public class EmojiEditText extends EditText {
	
	
	public EmojiEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public EmojiEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EmojiEditText(Context context) {
		super(context);
	}
	
	
	@Override
	protected void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		
		EmojiManager eManager = EmojiManager.getInstance();
		Spannable  ssb = getText();
		List<EmojiManager.EmojiMeta>	meta = eManager.searchEmoji(getContext(), ssb);
		
		if(meta != null){
			
			Object[]	spans = ssb.getSpans(0, ssb.length(), eManager.getWhatClass());
			for(Object obj: spans)
				ssb.removeSpan(obj);
			
			for(EmojiManager.EmojiMeta em: meta)
				ssb.setSpan(em.what, em.start, em.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			

		}
		
		
		//setText(text);
		//super.onTextChanged(text, start, lengthBefore, text.length());
		 
	}
	

}
