package nc.vo.so.saleinvoice;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-03-03 15:26:05
 */
@XmlRootElement(name = "datarow")//�ʸֲɹ�ϵͳ��Ʊ��ϸ
public class MsgAGCG000003 {
    String invoiceId;   //��Ʊ�ţ�����Ϣ������  ����:64
    String invoiceNum;   //��ƱƱ�����  ����:64
    String invoiceLineId;   //��Ʊ��ϸ�ţ���ϸ��Ϣ������  ����:64
    String bpoLineId;   //��ͬ�к�  ����:64
    String orderLineId;   //ִ�е��к�  ����:64
    String balanceLineId;   //���㵥�к�  ����:64
    String itemId;   //���ϱ���  ����:64
    String itemName;   //��������  ����:64
    BigDecimal invoicedQty;   //��Ʊ����  ����:32,3
    String Uom;   //������λ  ����:64
    BigDecimal netPrice;   //δ˰����  ����:32,6
    BigDecimal taxPrice;   //��˰����  ����:32,6
    BigDecimal taxRate;   //˰��  ����:32,2
    BigDecimal totNetAmt;   //δ˰�ܽ���Ʊ��  ����:32,2
    BigDecimal totAmt;   //��˰�ܽ���Ʊ��  ����:32,2
    BigDecimal totTaxAmt;   //��˰��  ����:32,2
    String balanceId;   //���㵥��  ����:64
    String bpoId;   //��ͬ��  ����:64
    String orderId;   //ִ�е���  ����:64
    String invoiceCode;   //��Ʊ����  ����:64
    String invoiceDate;   //��ƱƱ������  ����:14

    public MsgAGCG000003() {
    }

    public MsgAGCG000003(
            String invoiceId, String invoiceNum, String invoiceLineId, String bpoLineId, String orderLineId, String balanceLineId, String itemId, String itemName, BigDecimal invoicedQty, String Uom, BigDecimal netPrice, BigDecimal taxPrice, BigDecimal taxRate, BigDecimal totNetAmt, BigDecimal totAmt, BigDecimal totTaxAmt, String balanceId, String bpoId, String orderId, String invoiceCode, String invoiceDate
    ) {
        this.invoiceId = invoiceId;
        this.invoiceNum = invoiceNum;
        this.invoiceLineId = invoiceLineId;
        this.bpoLineId = bpoLineId;
        this.orderLineId = orderLineId;
        this.balanceLineId = balanceLineId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.invoicedQty = invoicedQty;
        this.Uom = Uom;
        this.netPrice = netPrice;
        this.taxPrice = taxPrice;
        this.taxRate = taxRate;
        this.totNetAmt = totNetAmt;
        this.totAmt = totAmt;
        this.totTaxAmt = totTaxAmt;
        this.balanceId = balanceId;
        this.bpoId = bpoId;
        this.orderId = orderId;
        this.invoiceCode = invoiceCode;
        this.invoiceDate = invoiceDate;


    }

    @XmlElement(name = "invoiceId")
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @XmlElement(name = "invoiceNum")
    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    @XmlElement(name = "invoiceLineId")
    public String getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(String invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    @XmlElement(name = "bpoLineId")
    public String getBpoLineId() {
        return bpoLineId;
    }

    public void setBpoLineId(String bpoLineId) {
        this.bpoLineId = bpoLineId;
    }

    @XmlElement(name = "orderLineId")
    public String getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(String orderLineId) {
        this.orderLineId = orderLineId;
    }

    @XmlElement(name = "balanceLineId")
    public String getBalanceLineId() {
        return balanceLineId;
    }

    public void setBalanceLineId(String balanceLineId) {
        this.balanceLineId = balanceLineId;
    }

    @XmlElement(name = "itemId")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @XmlElement(name = "itemName")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @XmlElement(name = "invoicedQty")
    public BigDecimal getInvoicedQty() {
        return invoicedQty;
    }

    public void setInvoicedQty(BigDecimal invoicedQty) {
        this.invoicedQty = invoicedQty;
    }

    @XmlElement(name = "uom")
    public String getUom() {
        return Uom;
    }

    public void setUom(String Uom) {
        this.Uom = Uom;
    }

    @XmlElement(name = "netPrice")
    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    @XmlElement(name = "taxPrice")
    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    @XmlElement(name = "taxRate")
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @XmlElement(name = "totNetAmt")
    public BigDecimal getTotNetAmt() {
        return totNetAmt;
    }

    public void setTotNetAmt(BigDecimal totNetAmt) {
        this.totNetAmt = totNetAmt;
    }

    @XmlElement(name = "totAmt")
    public BigDecimal getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(BigDecimal totAmt) {
        this.totAmt = totAmt;
    }

    @XmlElement(name = "totTaxAmt")
    public BigDecimal getTotTaxAmt() {
        return totTaxAmt;
    }

    public void setTotTaxAmt(BigDecimal totTaxAmt) {
        this.totTaxAmt = totTaxAmt;
    }

    @XmlElement(name = "balanceId")
    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    @XmlElement(name = "bpoId")
    public String getBpoId() {
        return bpoId;
    }

    public void setBpoId(String bpoId) {
        this.bpoId = bpoId;
    }

    @XmlElement(name = "orderId")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @XmlElement(name = "invoiceCode")
    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    @XmlElement(name = "invoiceDate")
    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }


}

