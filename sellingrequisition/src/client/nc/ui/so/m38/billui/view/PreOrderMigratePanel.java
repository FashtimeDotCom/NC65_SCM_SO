package nc.ui.so.m38.billui.view;

import java.awt.Color;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITextArea;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class PreOrderMigratePanel extends UIPanel {

	private static final long serialVersionUID = 2480663474733277960L;
	private UITextArea m_taTextArea = null;

	public void initUI() {
		setLayout(null);
		add(getTextAreaHint());
	}

	private UITextArea getTextAreaHint() {
		if (this.m_taTextArea == null) {
			try {
				StringBuffer sText = new StringBuffer();
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0119")/*\tԤ����Ǩ�Ʋ��裺*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0120")/*\t��1����װ ��������-�������ģ���ѣ�;*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0121")/*\t��2�������Ǩ�ơ���ť��*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0104")/*\tԤ����Ǩ��ע�����*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0105")/*\t��1�����۹����Ԥ����Ǩ�ƺ����ɶ�������Ԥ������ԭ���۹����Ԥ�������ݱ�ɾ�����Ҹù��̲�����;*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0107")/*\t��2��Ǩ�ƺ���������֧�ֽ���������Ԥ������Ϊ���󵥾��������ƻ�;*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0108")/*\t��3��Ǩ�ƺ��뽫��������Ԥ�������Զ������������Ԥ��������һ�¡�*/);
				
				this.m_taTextArea = new UITextArea();
				this.m_taTextArea.setName("UITextArea1");

				this.m_taTextArea.setRows(15);
				this.m_taTextArea.setLineWrap(true);
				this.m_taTextArea.setEditable(false);
				this.m_taTextArea.setEnabled(false);

				this.m_taTextArea.setBackground(getBackground());
				this.m_taTextArea.setDisabledTextColor(Color.BLACK);

				this.m_taTextArea.setText(sText.toString());
				this.m_taTextArea.setBounds(75, 30, 1050, 455);
				
			} catch (Exception ex) {
				ExceptionUtils.wrappException(ex);
			}
		}
		return this.m_taTextArea;
	}
}
