package com.lw.view;

import java.util.List;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lw.emoji.EmojiManager;

public class EmojiTextView extends TextView {
	
	
	public EmojiTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public EmojiTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EmojiTextView(Context context) {
		super(context);
	}
	
	
	@Override
	public void setText(CharSequence text, BufferType type) {
		EmojiManager eManager = EmojiManager.getInstance();
		SpannableStringBuilder ssb = new SpannableStringBuilder(text);
		List<EmojiManager.EmojiMeta>	meta = eManager.searchEmoji(getContext(), text);
		if(meta != null){
			
			Object[]	spans = ssb.getSpans(0, ssb.length(), eManager.getWhatClass());
			for(Object obj: spans)
				ssb.removeSpan(obj);
			
			for(EmojiManager.EmojiMeta em: meta)
				ssb.setSpan(em.what, em.start, em.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			text = ssb;
		}
		 
		super.setText(text, type);
	}

}
