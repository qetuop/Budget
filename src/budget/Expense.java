/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

//import static expensereport.Classification.ExpenseTypeEnum.UNKNOWN;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Brian
 */
public class Expense {

    private LongProperty transactionDate;
    private LongProperty postedDate;
    private StringProperty description;
    private DoubleProperty amount;
    private static DoubleProperty id;

    private static double counter = 0;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     *
     */
    public static final String TRANSACTION_DATE = "transaction_date";

    /**
     *
     */
    public static final String POSTED_DATE = "posted_date";

    /**
     *
     */
    public static final String AMOUNT = "amount";

    /**
     *
     */
    public static final String DESCRIPTION = "description";

    //Classification classification;
    /**
     *
     */
    public Expense() {
        this.transactionDate = new SimpleLongProperty(0);
        this.postedDate = new SimpleLongProperty(0);
        this.description = new SimpleStringProperty("");
        this.amount = new SimpleDoubleProperty(0.0);
        
        this.id = new SimpleDoubleProperty(counter++);
    }

    /**
     *
     * @param transactionDate
     * @param postedDate
     * @param description
     * @param amount
     */
    public Expense(Long transactionDate, Long postedDate, String description, Double amount) {
        setTransactionDate(transactionDate);
        setPostedDate(postedDate);
        setDiscription(description);
        setAmount(amount);
        
        this.id = new SimpleDoubleProperty(counter++);

        //this.classification     = new Classification(Classification.ExpenseTypeEnum.UNKNOWN);
        //System.out.format("%f\n",id);
    }

    /**
     *
     * @return
     */
    public double getAmount() {
        return amount.get();
    }

    /**
     *
     * @param value
     */
    public final void setAmount(Double value) {       
        Double old = amount.get();

        amount.set(value);

        PropertyChangeEvent evt = new PropertyChangeEvent(
                this, AMOUNT, old, amount.get());
        pcs.firePropertyChange(evt);
    }
    
    /**
     *
     * @return
     */
    public final DoubleProperty amountProperty() {
        return amount;
    }

    /**
     *
     * @return
     */
    public long getTransactionDate() {
        return transactionDate.get();
    }

    /**
     *
     * @param value
     */
    public final void setTransactionDate(Long value) {
        Long old = transactionDate.get();

        transactionDate.set(value);

        PropertyChangeEvent evt = new PropertyChangeEvent(
                this, TRANSACTION_DATE, old, transactionDate.get());
        pcs.firePropertyChange(evt);
    }
    
    /**
     *
     * @return
     */
    public final LongProperty transactionDateProperty() {
        return transactionDate;
    }

    /**
     *
     * @return
     */
    public long getPostedDate() {
        return postedDate.get();
    }

    /**
     *
     * @param value
     */
    public final void setPostedDate(Long value) {
        Long old = postedDate.get();

        postedDate.set(value);

        PropertyChangeEvent evt = new PropertyChangeEvent(
                this, POSTED_DATE, old, postedDate.get());
        pcs.firePropertyChange(evt);
    }
    
    /**
     *
     * @return
     */
    public final LongProperty postedDateProperty() {
        return postedDate;
    }
     
     /**
     *
     * @return
     */
    public String getDescription() {
        return description.get();
    }

    /**
     *
     * @param value
     */
    public final void setDiscription(String value) {
        String old = description.get();

        description.set(value);

        PropertyChangeEvent evt = new PropertyChangeEvent(
                this, DESCRIPTION, old, description.get());
        pcs.firePropertyChange(evt);
    }
    
    /**
     *
     * @return
     */
    public final StringProperty descriptionProperty() {
        return description;
    }

    /**
     *
     * @return
     */
    public String getTransactionDateString() {
        return dateString(getTransactionDate());
    }
    
    /**
     *
     * @return
     */
    public double getId() {
        return id.get();
    }

    /**
     *
     * @return
     */
    public String getPostedDateString() {
        return dateString(getPostedDate());
    }

    private String dateString(Long l) {
        Date now = new Date(l); // initialise to current UTC Date/Time
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setTimeZone(TimeZone.getDefault()); // local time
        String dateString = sdf.format(now);

        return dateString;
    }

    

    @Override
    public String toString() {
        String out = "";

        out += String.format("Posted Date:      %s\n", getPostedDateString());
        out += String.format("Transaction Date: %s\n", getTransactionDateString());
        out += String.format("Description:      %s\n", getDescription());
        out += String.format("Amount:           $%,.2f\n", getAmount());

        return out;
    }
    
    /**
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

}
