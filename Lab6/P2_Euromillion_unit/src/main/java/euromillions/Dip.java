package euromillions;

import java.util.Objects;

import sets.SetOfNaturals;

import java.util.Random;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A set of 5 numbers and 2 starts according to the Euromillions ranges.
 *
 * @author ico0
 */
public class Dip {


    private SetOfNaturals numbers;
    private SetOfNaturals starts;

    private static final int MAXSIZENUMBERS = 5;
    private static final int MAXSIZESTARS = 2;
    private static final int MAXRANGENUMBERS = 50;
    private static final int MAXRANGESTARS = 10;
    private static Logger logger = Logger.getLogger(Dip.class.getName());

    private static Random generator; 

    static {
        try {
            generator = SecureRandom.getInstance("NativePRNG");
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.INFO, "Mensagem", e); // Compliant
        }
    }

    public Dip() {
        numbers = new SetOfNaturals();
        starts = new SetOfNaturals();
    }

    public Dip(int[] arrayOfNumbers, int[] arrayOfStarts) {
        this();

        if (MAXSIZENUMBERS == arrayOfNumbers.length && MAXSIZESTARS == arrayOfStarts.length) {
            for (int n: arrayOfNumbers) {
                if (!(0 < n && n <= 50)) {
                    throw new IllegalArgumentException("wrong range of number in numbers array");
                }
            }
            for (int n: arrayOfStarts) {
                if (!(0 < n && n <= 10)) {
                    throw new IllegalArgumentException("wrong range of number in stars array");
                }
            }
            numbers.add(arrayOfNumbers);
            starts.add(arrayOfStarts);
        } else {
            throw new IllegalArgumentException("wrong number of elements in numbers/stars");
        }

    }

    public SetOfNaturals getNumbersColl() {
        return numbers;
    }

    public SetOfNaturals getStarsColl() {
        return starts;
    }

    public static Dip generateRandomDip() {

        Dip randomDip = new Dip();
        for (int i = 0; i < MAXSIZENUMBERS; i++) {
            int candidate = generator.nextInt(MAXRANGENUMBERS-1) + 1;
            if (!randomDip.getNumbersColl().contains(candidate)) {
                randomDip.getNumbersColl().add(candidate);
            }

        }
        for (int i = 0; i < MAXSIZESTARS; i++) {
            int candidate = generator.nextInt(MAXRANGESTARS-1) + 1;
            if (!randomDip.getStarsColl().contains(candidate)) {
                randomDip.getStarsColl().add(candidate);
            }
        }
        return randomDip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.numbers);
        hash = 29 * hash + Objects.hashCode(this.starts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dip other = (Dip) obj;
        if (!Objects.equals(this.numbers, other.numbers)) {
            return false;
        }
        return Objects.equals(this.starts, other.starts);
    }


    /**
     * prepares a string representation of the data structure, formated for
     * printing
     *
     * @return formatted string with data
     */
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append("N[");
        for (int number : getNumbersColl()) {
            sb.append(String.format("%3d", number));
        }
        sb.append("] S[");
        for (int star : getStarsColl()) {
            sb.append(String.format("%3d", star));
        }
        sb.append("]");
        return sb.toString();
    }
}
