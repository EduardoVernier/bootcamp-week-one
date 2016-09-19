package com.iws.futurefaces.weekone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

	private int[] layouts;
	private ViewPager viewPager;
	private MyViewPagerAdapter viewPagerAdapter;
	private TextView[] dots;
	LinearLayout dotsLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		getSupportActionBar().hide();

		layouts = new int[]{
				R.layout.welcome_1,
				R.layout.welcome_2,
				R.layout.welcome_3};

		addBottomDots(0);

		viewPager = (ViewPager) findViewById(R.id.view_pager);
		MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter();
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


		Button goButton = (Button) findViewById(R.id.go);
		goButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void addBottomDots(int currentPage) {

		dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
		dots = new TextView[layouts.length];

		dotsLayout.removeAllViews();
		for (int i = 0; i < dots.length; i++) {
			dots[i] = new TextView(this);
			dots[i].setText(Html.fromHtml("&#8226;"));
			dots[i].setTextSize(35);
			dots[i].setTextColor(Color.parseColor("#aaaaaa"));
			dotsLayout.addView(dots[i]);
		}

		if (dots.length > 0)
			dots[currentPage].setTextColor(Color.parseColor("#eeeeee"));
	}

	private int getItem(int i) {
		return viewPager.getCurrentItem() + i;
	}

	private void launchHomeScreen() {
		startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
		finish();
	}

	ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

		@Override
		public void onPageSelected(int position) {
			addBottomDots(position);
			if (position == 2)
				findViewById(R.id.go).setVisibility(View.VISIBLE);
			else
				findViewById(R.id.go).setVisibility(View.GONE);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};

	public class MyViewPagerAdapter extends PagerAdapter {
		private LayoutInflater layoutInflater;

		public MyViewPagerAdapter() {
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View view = layoutInflater.inflate(layouts[position], container, false);
			container.addView(view);

			return view;
		}

		@Override
		public int getCount() {
			return layouts.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}


		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View view = (View) object;
			container.removeView(view);
		}
	}

}
