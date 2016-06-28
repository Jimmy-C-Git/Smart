package com.cj.threedimslidinglayout;

import com.cj.smart.R;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.view.View.OnTouchListener;
public class ThreeDSlidingLayout extends RelativeLayout implements OnTouchListener {

	/**
	 * ������ʾ��������಼��ʱ����ָ������Ҫ�ﵽ���ٶȡ�
	 */
	public static final int SNAP_VELOCITY = 200;

	/**
	 * ����״̬��һ�֣���ʾδ�����κλ�����
	 */
	public static final int DO_NOTHING = 0;

	/**
	 * ����״̬��һ�֣���ʾ���ڻ������˵���
	 */
	public static final int SHOW_MENU = 1;

	/**
	 * ����״̬��һ�֣���ʾ�����������˵���
	 */
	public static final int HIDE_MENU = 2;

	/**
	 * ��¼��ǰ�Ļ���״̬
	 */
	private int slideState;

	/**
	 * ��Ļ���ֵ��
	 */
	private int screenWidth;

	/**
	 * �Ҳ಼�������Ի����������Ե��
	 */
	private int leftEdge = 0;

	/**
	 * �Ҳ಼�������Ի��������ұ�Ե��
	 */
	private int rightEdge = 0;

	/**
	 * �ڱ��ж�Ϊ����֮ǰ�û���ָ�����ƶ������ֵ��
	 */
	private int touchSlop;

	/**
	 * ��¼��ָ����ʱ�ĺ����ꡣ
	 */
	private float xDown;

	/**
	 * ��¼��ָ����ʱ�������ꡣ
	 */
	private float yDown;

	/**
	 * ��¼��ָ�ƶ�ʱ�ĺ����ꡣ
	 */
	private float xMove;

	/**
	 * ��¼��ָ�ƶ�ʱ�������ꡣ
	 */
	private float yMove;

	/**
	 * ��¼�ֻ�̧��ʱ�ĺ����ꡣ
	 */
	private float xUp;

	/**
	 * ��಼�ֵ�ǰ����ʾ�������ء�ֻ����ȫ��ʾ������ʱ�Ż���Ĵ�ֵ�����������д�ֵ��Ч��
	 */
	private boolean isLeftLayoutVisible;

	/**
	 * �Ƿ����ڻ�����
	 */
	private boolean isSliding;

	/**
	 * �Ƿ��Ѽ��ع�һ��layout������onLayout�еĳ�ʼ��ֻ�����һ��
	 */
	private boolean loadOnce;

	/**
	 * ��಼�ֶ���
	 */
	private View leftLayout;

	/**
	 * �Ҳ಼�ֶ���
	 */
	private View rightLayout;

	/**
	 * �ڻ���������չʾ��3D��ͼ
	 */
	private Image3dView image3dView;

	/**
	 * ���ڼ����໬�¼���View��
	 */
	private View mBindView;

	/**
	 * ��಼�ֵĲ�����ͨ���˲���������ȷ����಼�ֵĿ�ȣ��Լ�����leftMargin��ֵ��
	 */
	private MarginLayoutParams leftLayoutParams;

	/**
	 * �Ҳ಼�ֵĲ�����ͨ���˲���������ȷ���Ҳ಼�ֵĿ�ȡ�
	 */
	private MarginLayoutParams rightLayoutParams;

	/**
	 * 3D��ͼ�Ĳ�����ͨ���˲���������ȷ��3D��ͼ�Ŀ�ȡ�
	 */
	private ViewGroup.LayoutParams image3dViewParams;

	/**
	 * ���ڼ�����ָ�������ٶȡ�
	 */
	private VelocityTracker mVelocityTracker;

	/**
	 * ��дSlidingLayout�Ĺ��캯�������л�ȡ����Ļ�Ŀ�ȡ�
	 * 
	 * @param context
	 * @param attrs
	 */
	public ThreeDSlidingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	/**
	 * �󶨼����໬�¼���View�����ڰ󶨵�View���л����ſ�����ʾ��������಼�֡�
	 * 
	 * @param bindView
	 *            ��Ҫ�󶨵�View����
	 */
	public void setScrollEvent(View bindView) {
		mBindView = bindView;
		mBindView.setOnTouchListener(this);
	}

	/**
	 * ����Ļ��������಼�ֽ��棬�����ٶ��趨Ϊ10.
	 */
	public void scrollToLeftLayout() {
		image3dView.clearSourceBitmap();
		new ScrollTask().execute(-10);
	}

	/**
	 * ����Ļ�������Ҳ಼�ֽ��棬�����ٶ��趨Ϊ-10.
	 */
	public void scrollToRightLayout() {
		image3dView.clearSourceBitmap();
		new ScrollTask().execute(10);
	}

	/**
	 * ��಼���Ƿ���ȫ��ʾ����������ȫ���أ����������д�ֵ��Ч��
	 * 
	 * @return ��಼����ȫ��ʾ����true����ȫ���ط���false��
	 */
	public boolean isLeftLayoutVisible() {
		return isLeftLayoutVisible;
	}

	/**
	 * ��onLayout�������趨��಼�ֺ��Ҳ಼�ֵĲ�����
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed && !loadOnce) {
			// ��ȡ��಼�ֶ���
			leftLayout = findViewById(R.id.menu);
			leftLayoutParams = (MarginLayoutParams) leftLayout.getLayoutParams();
			rightEdge = -leftLayoutParams.width;
			// ��ȡ�Ҳ಼�ֶ���
			rightLayout = findViewById(R.id.content);
			rightLayoutParams = (MarginLayoutParams) rightLayout.getLayoutParams();
			rightLayoutParams.width = screenWidth;
			rightLayout.setLayoutParams(rightLayoutParams);
			// ��ȡ3D��ͼ����
			image3dView = (Image3dView) findViewById(R.id.image_3d_view);
			// ����಼�ִ���3D��ͼ����Ϊ����Դ
			image3dView.setSourceView(leftLayout);
			loadOnce = true;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		createVelocityTracker(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// ��ָ����ʱ����¼����ʱ�ĺ�����
			xDown = event.getRawX();
			yDown = event.getRawY();
			slideState = DO_NOTHING;
			break;
		case MotionEvent.ACTION_MOVE:
			// ��ָ�ƶ�ʱ���ԱȰ���ʱ�ĺ����꣬������ƶ��ľ��룬�������Ҳ಼�ֵ�leftMarginֵ���Ӷ���ʾ��������಼��
			xMove = event.getRawX();
			yMove = event.getRawY();
			int moveDistanceX = (int) (xMove - xDown);
			int moveDistanceY = (int) (yMove - yDown);
			checkSlideState(moveDistanceX, moveDistanceY);
			switch (slideState) {
			case SHOW_MENU:
				rightLayoutParams.rightMargin = -moveDistanceX;
				onSlide();
				break;
			case HIDE_MENU:
				rightLayoutParams.rightMargin = rightEdge - moveDistanceX;
				onSlide();
				break;
			default:
				break;
			}
			break;
		case MotionEvent.ACTION_UP:
			xUp = event.getRawX();
			int upDistanceX = (int) (xUp - xDown);
			if (isSliding) {
				// ��ָ̧��ʱ�������жϵ�ǰ���Ƶ���ͼ
				switch (slideState) {
				case SHOW_MENU:
					if (shouldScrollToLeftLayout()) {
						scrollToLeftLayout();
					} else {
						scrollToRightLayout();
					}
					break;
				case HIDE_MENU:
					if (shouldScrollToRightLayout()) {
						scrollToRightLayout();
					} else {
						scrollToLeftLayout();
					}
					break;
				default:
					break;
				}
			} else if (upDistanceX < touchSlop && isLeftLayoutVisible) {
				scrollToRightLayout();
			}
			recycleVelocityTracker();
			break;
		}
		if (v.isEnabled()) {
			if (isSliding) {
				unFocusBindView();
				return true;
			}
			if (isLeftLayoutVisible) {
				return true;
			}
			return false;
		}
		return true;
	}

	/**
	 * ִ�л��������е��߼���������߽��飬�ı�ƫ��ֵ���ɼ��Լ��ȡ�
	 */
	private void onSlide() {
		checkSlideBorder();
		rightLayout.setLayoutParams(rightLayoutParams);
		image3dView.clearSourceBitmap();
		image3dViewParams = image3dView.getLayoutParams();
		image3dViewParams.width = -rightLayoutParams.rightMargin;
		// ������ͬʱ�ı�3D��ͼ�Ĵ�С
		image3dView.setLayoutParams(image3dViewParams);
		// ��֤�ڻ���������3D��ͼ�ɼ�����಼�ֲ��ɼ�
		showImage3dView();
	}

	/**
	 * ������ָ�ƶ��ľ��룬�жϵ�ǰ�û��Ļ�����ͼ��Ȼ���slideState��ֵ����Ӧ�Ļ���״ֵ̬��
	 * 
	 * @param moveDistanceX
	 *            �����ƶ��ľ���
	 * @param moveDistanceY
	 *            �����ƶ��ľ���
	 */
	private void checkSlideState(int moveDistanceX, int moveDistanceY) {
		if (isLeftLayoutVisible) {
			if (!isSliding && Math.abs(moveDistanceX) >= touchSlop && moveDistanceX < 0) {
				isSliding = true;
				slideState = HIDE_MENU;
			}
		} else if (!isSliding && Math.abs(moveDistanceX) >= touchSlop && moveDistanceX > 0
				&& Math.abs(moveDistanceY) < touchSlop) {
			isSliding = true;
			slideState = SHOW_MENU;
		}
	}

	/**
	 * �ڻ��������м�����˵��ı߽�ֵ����ֹ�󶨲��ֻ�����Ļ��
	 */
	private void checkSlideBorder() {
		if (rightLayoutParams.rightMargin > leftEdge) {
			rightLayoutParams.rightMargin = leftEdge;
		} else if (rightLayoutParams.rightMargin < rightEdge) {
			rightLayoutParams.rightMargin = rightEdge;
		}
	}

	/**
	 * �ж��Ƿ�Ӧ�ù�������಼��չʾ�����������ָ�ƶ����������Ļ��1/2��������ָ�ƶ��ٶȴ���SNAP_VELOCITY��
	 * ����ΪӦ�ù�������಼��չʾ������
	 * 
	 * @return ���Ӧ�ù�������಼��չʾ��������true�����򷵻�false��
	 */
	private boolean shouldScrollToLeftLayout() {
		return xUp - xDown > leftLayoutParams.width / 2 || getScrollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * �ж��Ƿ�Ӧ�ù������Ҳ಼��չʾ�����������ָ�ƶ��������leftLayoutPadding������Ļ��1/2��
	 * ������ָ�ƶ��ٶȴ���SNAP_VELOCITY�� ����ΪӦ�ù������Ҳ಼��չʾ������
	 * 
	 * @return ���Ӧ�ù������Ҳ಼��չʾ��������true�����򷵻�false��
	 */
	private boolean shouldScrollToRightLayout() {
		return xDown - xUp > leftLayoutParams.width / 2 || getScrollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * ����VelocityTracker���󣬲��������¼����뵽VelocityTracker���С�
	 * 
	 * @param event
	 *            �Ҳ಼�ּ����ؼ��Ļ����¼�
	 */
	private void createVelocityTracker(MotionEvent event) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}

	/**
	 * ��ȡ��ָ���Ҳ಼�ֵļ���View�ϵĻ����ٶȡ�
	 * 
	 * @return �����ٶȣ���ÿ�����ƶ��˶�������ֵΪ��λ��
	 */
	private int getScrollVelocity() {
		mVelocityTracker.computeCurrentVelocity(1000);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}

	/**
	 * ����VelocityTracker����
	 */
	private void recycleVelocityTracker() {
		mVelocityTracker.recycle();
		mVelocityTracker = null;
	}

	/**
	 * ʹ�ÿ��Ի�ý���Ŀؼ��ڻ�����ʱ��ʧȥ���㡣
	 */
	private void unFocusBindView() {
		if (mBindView != null) {
			mBindView.setPressed(false);
			mBindView.setFocusable(false);
			mBindView.setFocusableInTouchMode(false);
		}
	}

	/**
	 * ��֤��ʱ����಼�ֲ��ɼ���3D��ͼ�ɼ����Ӷ��û��������в���3D��Ч����
	 */
	private void showImage3dView() {
		if (image3dView.getVisibility() != View.VISIBLE) {
			image3dView.setVisibility(View.VISIBLE);
		}
		if (leftLayout.getVisibility() != View.INVISIBLE) {
			leftLayout.setVisibility(View.INVISIBLE);
		}
	}

	class ScrollTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... speed) {
			int rightMargin = rightLayoutParams.rightMargin;
			// ���ݴ�����ٶ����������棬������������߽���ұ߽�ʱ������ѭ����
			while (true) {
				rightMargin = rightMargin + speed[0];
				if (rightMargin < rightEdge) {
					rightMargin = rightEdge;
					break;
				}
				if (rightMargin > leftEdge) {
					rightMargin = leftEdge;
					break;
				}
				publishProgress(rightMargin);
				// Ϊ��Ҫ�й���Ч��������ÿ��ѭ��ʹ�߳�˯��5���룬�������۲��ܹ���������������
				sleep(5);
			}
			if (speed[0] > 0) {
				isLeftLayoutVisible = false;
			} else {
				isLeftLayoutVisible = true;
			}
			isSliding = false;
			return rightMargin;
		}

		@Override
		protected void onProgressUpdate(Integer... rightMargin) {
			rightLayoutParams.rightMargin = rightMargin[0];
			rightLayout.setLayoutParams(rightLayoutParams);
			image3dViewParams = image3dView.getLayoutParams();
			image3dViewParams.width = -rightLayoutParams.rightMargin;
			image3dView.setLayoutParams(image3dViewParams);
			showImage3dView();
			unFocusBindView();
		}

		@Override
		protected void onPostExecute(Integer rightMargin) {
			rightLayoutParams.rightMargin = rightMargin;
			rightLayout.setLayoutParams(rightLayoutParams);
			image3dViewParams = image3dView.getLayoutParams();
			image3dViewParams.width = -rightLayoutParams.rightMargin;
			image3dView.setLayoutParams(image3dViewParams);
			if (isLeftLayoutVisible) {
				// ��֤�ڻ�����������಼�ֿɼ���3D��ͼ���ɼ���
				image3dView.setVisibility(View.INVISIBLE);
				leftLayout.setVisibility(View.VISIBLE);
			}
		}
	}

	/**
	 * ʹ��ǰ�߳�˯��ָ���ĺ�������
	 * 
	 * @param millis
	 *            ָ����ǰ�߳�˯�߶�ã��Ժ���Ϊ��λ
	 */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}