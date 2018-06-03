package mymouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayMouse extends JFrame implements Runnable {

	private int num = 0;
	private JLabel back;
	private JLabel[] mouses;
	private JLabel df;
	private ImageIcon imgMouse;
	
	// 初始化-界面布局
	public PlayMouse() {
		setResizable(false);
		setTitle("打地鼠");
		setIconImage(new ImageIcon("tubiao.png").getImage());
		setLayout(null);
		setBounds(500, 200, 849, 640);
	
		// 设置鼠标图标
		setCursor(Toolkit.getDefaultToolkit().
					createCustomCursor(Toolkit.getDefaultToolkit().getImage("cz.png"), new Point(), "Cz"));
		
		// 设置背景图
		back = new JLabel();
		ImageIcon icon = new ImageIcon("back.png");
		back.setIcon(icon);
		back.setBounds(0, 0, 849, 640);
		
		// 设置地鼠图片
		imgMouse = new ImageIcon("ms.png");
		imgMouse.setImage(imgMouse.getImage().getScaledInstance(100, 122, Image.SCALE_DEFAULT));
		mouses = new JLabel[9];
		for (int i = 0; i < mouses.length; i++) {
			mouses[i] = new JLabel();
			mouses[i].setSize(100, 122);
			
			// 添加点击事件
			mouses[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					Object obj = e.getSource();
					if( obj instanceof JLabel) {
						JLabel label = (JLabel)obj;
						if( label .getIcon() != null ) {
							repaint();
							num++;
							label.setIcon(null);
							df.setText("您的得分是: "+ num+ "分");
						}
					}
				}
			});
			add(mouses[i]);
		}
		
		// 设置地鼠位置
		mouses[0].setLocation(205, 92);
		mouses[1].setLocation(372, 92);
		mouses[2].setLocation(542, 92);

		mouses[3].setLocation(175, 217);
		mouses[4].setLocation(370, 217);
		mouses[5].setLocation(565, 217);

		mouses[6].setLocation(150, 357);
		mouses[7].setLocation(370, 357);
		mouses[8].setLocation(588, 357);
		
		// 设置得分面板
		df = new JLabel();
		df.setText("您的得分是： 分");
		df.setFont(new Font("微软雅黑", Font.BOLD, 25));
		df.setForeground(Color.blue);
		df.setBounds(542, 15, 300, 50);
		add(df);
		
		add(back);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		PlayMouse p = new PlayMouse();
		Thread t = new Thread(p);
		t.start();
	}
	
	// 多线程
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);   				// 地鼠显示间隔时间
				int idx = (int)(Math.random()*9);
				if( mouses[idx].getIcon() == null ) {
					mouses[idx].setIcon(imgMouse);
					Thread.sleep(800);  			// 地鼠出现停留时间
					if( mouses[idx].isShowing() ) {
						mouses[idx].setIcon(null);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
