package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

import Logic.Logic;

public class Pjesa_lojes extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel cardsPanel, tablePanel;
	ArrayList<Permbajtja_letres> cards;
	Permbajtja_lojes Permbajtja_lojes;
	private Color Background = new Color(255,218,185);
	private Color Background1 = new Color(176,48,96);

	public void CardClick(ActionEvent e) {
		Card cTmp = (Card) e.getSource();

		this.cardsPanel.remove(cTmp);
		this.cardsPanel.repaint();
		this.tablePanel.repaint();
		this.revalidate();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (Permbajtja_lojes.getCurrentPlayer() == Permbajtja_lojes.getIndex()) {

			Card cTmp = (Card) e.getSource();//gives you a reference to the object that the event came from, i.e button
			if (Permbajtja_lojes.getTableSuite() == -1 || cTmp.getCardData().getSuit() == Permbajtja_lojes.getTableSuite()
					|| !Logic.has(Permbajtja_lojes.getTableSuite(), this.getCards())) {

				this.cardsPanel.remove(cTmp);
				this.cardsPanel.repaint();
				this.tablePanel.repaint();
				this.revalidate();
				Permbajtja_lojes.Send("C_CPD" + cTmp.toString());
				Logic.sleep(1000);
				Permbajtja_lojes.setCurrentPlayer(-1);
			}
		}

	}

	public Pjesa_lojes(Permbajtja_lojes gmfrm) {

		this.Permbajtja_lojes = gmfrm;
		this.setLayout(new BorderLayout(5, 5));

		this.cardsPanel = new JPanel();
		this.cardsPanel.setBackground(Background1);
		this.cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 10));

		this.tablePanel = new JPanel();
		this.tablePanel.setBackground(Background);
		this.tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.add(cardsPanel, BorderLayout.SOUTH);
		this.add(tablePanel, BorderLayout.CENTER);

		System.out.println("GPanel Created");
	}

	public void ReDistributeCards(ArrayList<Permbajtja_letres> cards) {
		this.cardsPanel.removeAll();
		this.cards = cards;

		for (Permbajtja_letres card : this.cards) {
			Card c = new Card(card);
			this.cardsPanel.add(c);
			c.addActionListener(this);
		}
		this.revalidate();
	}

	public JPanel getCardsPanel() {
		return cardsPanel;
	}
 //Shton kartat ne panel
	public void addCard(Card card) {

		this.tablePanel.add(card);
		tablePanel.repaint();
		tablePanel.revalidate();
		this.repaint();
		this.revalidate();
		System.out.println("Card Added");
	}

	public ArrayList<Permbajtja_letres> getCards() {
		ArrayList<Permbajtja_letres> cards = new ArrayList<Permbajtja_letres>();
		for (int i = 0; i < cardsPanel.getComponentCount(); i++) {
			if (cardsPanel.getComponent(i) instanceof Card) {
				cards.add(((Card) cardsPanel.getComponent(i)).getCardData());
			}
		}
		return cards;
	}

}
