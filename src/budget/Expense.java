/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package budget;

//import static expensereport.Classification.ExpenseTypeEnum.UNKNOWN;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Brian
 */
public class Expense {
    Long        transactionDate;
    Long        postedDate;
    String      description;
    Double      amount;
    
    //Classification classification;

    
    public Expense(Long transactionDate, Long postedDate, String description, Double amount) {
        this.transactionDate    = transactionDate;
        this.postedDate         = postedDate;
        this.description        = description;
        this.amount             = amount;
        //this.classification     = new Classification(Classification.ExpenseTypeEnum.UNKNOWN);

        //System.out.format("%f\n",id);
    }

    public Double getAmount() {
        return amount;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }
    
    public Long getPostedDate() {
        return postedDate;
    }

    public String getTransactionDateString() {
        return dateString(getTransactionDate());
    }
    
    public String getPostedDateString() {

        return dateString(getPostedDate());
    }
    
    private String dateString(Long l){
        Date now = new Date(l); // initialise to current UTC Date/Time
        SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
        sdf.setTimeZone( TimeZone.getDefault() ); // local time
        String dateString = sdf.format( now );
        
        return dateString;
    }

    public String getdescription() {
        return description;
    }



    @Override
    public String toString(){
        String out = "";
        
        out += String.format("Posted Date:      %s\n",getPostedDateString());
        out += String.format("Transaction Date: %s\n",getTransactionDateString());
        out += String.format("Description:      %s\n",description);
        out += String.format("Amount:           $%,.2f\n",amount);
     

        return out;
    }
}
