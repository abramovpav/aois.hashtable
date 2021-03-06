package by.bsuir.iit.abramov.aois.hashtable.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import by.bsuir.iit.abramov.aois.hashtable.model.HashTable;

public class Form extends JFrame {

	private final JPanel	contentPane;
	private final JTextArea	text;
	private final HashTable	hash;

	public Form(final HashTable hash) {

		super();
		this.hash = hash;
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		text = new JTextArea();
		text.setText(hash.getString());
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(text, BorderLayout.CENTER);
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel, BorderLayout.SOUTH);
		JButton button = new JButton("put");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {

				final String id = JOptionPane.showInputDialog("������� ����");
				final String str = JOptionPane.showInputDialog("������� �����");
				final Integer data = Integer.valueOf(str);
				hash.put(id, data);
				text.setText(hash.getString());
			}
		});
		panel.add(button, BorderLayout.EAST);
		button = new JButton("remove");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {

				final String id = JOptionPane.showInputDialog("������� ����:");
				hash.remove(id);
				text.setText(hash.getString());

			}
		});
		panel.add(button, BorderLayout.CENTER);
		button = new JButton("get");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {

				final String id = JOptionPane.showInputDialog("������� ����:");
				final Integer data = hash.get(id);
				JOptionPane.showMessageDialog(null, "data: " + Integer.toString(data));

			}
		});
		panel.add(button, BorderLayout.WEST);

	}
}
