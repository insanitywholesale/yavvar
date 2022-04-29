package ooperg005;

public class TaxinomisiMethods {

    public static void bubbleSort(Foititis[] pin) {
        int n = pin.length;
        Foititis temp = null;
        for (int i = 0; i < pin.length; i++) {
            System.out.println(pin[i]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if ((pin[j - 1].getOnomatEpwnymo().compareTo(pin[j].getOnomatEpwnymo())) > 0) {
                    temp = pin[j - 1];
                    pin[j - 1] = pin[j];
                    pin[j] = temp;
                }
            }
        }
        for (int i = 0; i < pin.length; i++) {
            System.out.println(pin[i]);
        }
    }

    public static void insertionSort(Foititis[] pin) {
        int n = pin.length;
        for (int i = 0; i < pin.length; i++) {
            System.out.println(pin[i]);
        }
        for (int j = 1; j < n; j++) {
            Foititis key = pin[j];
            int i = j - 1;
            while ((i > -1) && ((pin[i].getThlefwno().compareTo(key.getThlefwno())) > 0)) {
                pin[i + 1] = pin[i];
                i--;
            }
            pin[i + 1] = key;
        }
        for (int i = 0; i < pin.length; i++) {
            System.out.println(pin[i]);
        }
    }

    public static void selectionSort(Foititis[] pin) {
        int n = pin.length;
        for (int i = 0; i < n; i++) {
            System.out.println(pin[i]);
        }
        for (int i = 0; i < n - 1; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (pin[j].getMesosOros() < pin[index].getMesosOros()) {
                    index = j;
                }
            }
            Foititis smallerFoititis = pin[index];
            pin[index] = pin[i];
            pin[i] = smallerFoititis;
        }
        for (int i = 0; i < n; i++) {
            System.out.println(pin[i]);
        }
    }
}
