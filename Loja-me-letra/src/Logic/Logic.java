package Logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import GUI.Permbajtja_letres;

public class Logic {

	private static ArrayList<Permbajtja_letres> generateCardsSet() {
		ArrayList<Permbajtja_letres> cards = new ArrayList<Permbajtja_letres>();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				cards.add(new Permbajtja_letres(j + 2, i + 1));

			}
		}
		return cards;
	}

	public static ArrayList<ArrayList<Permbajtja_letres>> generatePlayersCardSets() {
		ArrayList<ArrayList<Permbajtja_letres>> playersSets = new ArrayList<ArrayList<Permbajtja_letres>>();
		ArrayList<Permbajtja_letres> allCards = generateCardsSet();

		boolean[] selected = new boolean[52];

		for (int i = 0; i < 52; i++)
			selected[i] = false;

		Random rnd = new Random(new Date().getTime());
		int choice = rnd.nextInt(52);

		for (int i = 0; i < 4; i++) {
			ArrayList<Permbajtja_letres> tmp = new ArrayList<Permbajtja_letres>();

			for (int j = 0; j < 13; j++) {
				while (selected[choice]) {
					choice = rnd.nextInt(52);
				}

				tmp.add(allCards.get(choice));
				selected[choice] = true;
			}

			playersSets.add(SortToSuits(QuickSort(tmp)));
		}

		return playersSets;

	}

	private static ArrayList<Permbajtja_letres> QuickSort(ArrayList<Permbajtja_letres> lst)

	{
		if (lst.size() <= 1)
			return lst;

		ArrayList<Permbajtja_letres> less = new ArrayList<Permbajtja_letres>();
		ArrayList<Permbajtja_letres> more = new ArrayList<Permbajtja_letres>();
		Permbajtja_letres me = lst.get(0);

		for (int i = 1; i < lst.size(); i++) {
			if (me.getValue() > lst.get(i).getValue())
				less.add(lst.get(i));
			else
				more.add(lst.get(i));
		}

		more = QuickSort(more);
		less = QuickSort(less);

		more.add(me);
		more.addAll(less);

		return (more);
	}

	public static ArrayList<Permbajtja_letres> SortToSuits(ArrayList<Permbajtja_letres> unSorted) {
		// Sortimi i elementeve ne nje ArrayList

		ArrayList<Permbajtja_letres> Sorted = new ArrayList<Permbajtja_letres>();
		int indx = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < unSorted.size(); j++) {
				if ((i + 1) == unSorted.get(j).getSuit()) {
					Sorted.add(unSorted.get(j));
				}
			}

			indx++;
		}

		return Sorted;

	}

	public static ArrayList<Permbajtja_letres> DeSerializeCards(String StrCards) {
		ArrayList<Permbajtja_letres> Cards = new ArrayList<Permbajtja_letres>();

		String[] clst = StrCards.split("-");

		for (int i = 0; i < clst.length; i++) {
			int value = Integer.parseInt(clst[i].split(",")[0]);
			int suit = Integer.parseInt(clst[i].split(",")[1]);
			Cards.add(new Permbajtja_letres(value, suit));
		}

		return Cards;
	}

	public static String SerializeCards(ArrayList<Permbajtja_letres> Cards) {
		String strCards = "";
		for (Permbajtja_letres card : Cards) {
			strCards += "" + card.getValue() + "," + card.getSuit() + "-";
		}
		return strCards;
	}

	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception ex) {

		}
	}

	public static boolean has(int suit, ArrayList<Permbajtja_letres> cards) {
		boolean result = false;
		for (Permbajtja_letres card : cards) {
			result = result || (card.getSuit() == suit);
		}
		return result;
	}

	public static boolean Dokk(ArrayList<Permbajtja_letres> cards, ArrayList<Integer> Modes) {

		if (Modes.contains(new Integer(2)))// King of Hearts
		{
			int heartCount = 0;
			boolean hasKing = false;
			for (Permbajtja_letres card : cards)
				if (card.getSuit() == 2) {
					heartCount++;
					hasKing = (card.getValue() == 13);

				}
			if (heartCount <= 2 && hasKing)
				return true;

		}

		if (Modes.contains(new Integer(5)))// Suns
		{

			int points = 0;
			for (Permbajtja_letres card : cards) {
				if (card.getValue() > 10)
					points += card.getValue() - 10;
			}
			if (points >= 16)
				return true;
		}

		return false;
	}

	public static int HandWinner(int tableSuit, Permbajtja_letres[] Cards) {
		int max = 0;
		int indx = 0;
		for (int i = 0; i < Cards.length; i++) {
			if (Cards[i].getSuit() == tableSuit) {
				if (Cards[i].getValue() > max) {
					max = Cards[i].getValue();
					indx = i;
				}
			}
		}
		return indx;
	}
}
