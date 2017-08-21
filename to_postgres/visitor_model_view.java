// ORM class for table 'visitor_model_view'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Sun Aug 20 21:37:39 UTC 2017
// For connector: org.apache.sqoop.manager.PostgresqlManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class visitor_model_view extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("mt_date", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        visitor_model_view.this.mt_date = (java.sql.Date)value;
      }
    });
    setters.put("visitor_id_hash", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        visitor_model_view.this.visitor_id_hash = (String)value;
      }
    });
    setters.put("model_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        visitor_model_view.this.model_id = (Integer)value;
      }
    });
    setters.put("model_views", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        visitor_model_view.this.model_views = (Long)value;
      }
    });
  }
  public visitor_model_view() {
    init0();
  }
  private java.sql.Date mt_date;
  public java.sql.Date get_mt_date() {
    return mt_date;
  }
  public void set_mt_date(java.sql.Date mt_date) {
    this.mt_date = mt_date;
  }
  public visitor_model_view with_mt_date(java.sql.Date mt_date) {
    this.mt_date = mt_date;
    return this;
  }
  private String visitor_id_hash;
  public String get_visitor_id_hash() {
    return visitor_id_hash;
  }
  public void set_visitor_id_hash(String visitor_id_hash) {
    this.visitor_id_hash = visitor_id_hash;
  }
  public visitor_model_view with_visitor_id_hash(String visitor_id_hash) {
    this.visitor_id_hash = visitor_id_hash;
    return this;
  }
  private Integer model_id;
  public Integer get_model_id() {
    return model_id;
  }
  public void set_model_id(Integer model_id) {
    this.model_id = model_id;
  }
  public visitor_model_view with_model_id(Integer model_id) {
    this.model_id = model_id;
    return this;
  }
  private Long model_views;
  public Long get_model_views() {
    return model_views;
  }
  public void set_model_views(Long model_views) {
    this.model_views = model_views;
  }
  public visitor_model_view with_model_views(Long model_views) {
    this.model_views = model_views;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof visitor_model_view)) {
      return false;
    }
    visitor_model_view that = (visitor_model_view) o;
    boolean equal = true;
    equal = equal && (this.mt_date == null ? that.mt_date == null : this.mt_date.equals(that.mt_date));
    equal = equal && (this.visitor_id_hash == null ? that.visitor_id_hash == null : this.visitor_id_hash.equals(that.visitor_id_hash));
    equal = equal && (this.model_id == null ? that.model_id == null : this.model_id.equals(that.model_id));
    equal = equal && (this.model_views == null ? that.model_views == null : this.model_views.equals(that.model_views));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof visitor_model_view)) {
      return false;
    }
    visitor_model_view that = (visitor_model_view) o;
    boolean equal = true;
    equal = equal && (this.mt_date == null ? that.mt_date == null : this.mt_date.equals(that.mt_date));
    equal = equal && (this.visitor_id_hash == null ? that.visitor_id_hash == null : this.visitor_id_hash.equals(that.visitor_id_hash));
    equal = equal && (this.model_id == null ? that.model_id == null : this.model_id.equals(that.model_id));
    equal = equal && (this.model_views == null ? that.model_views == null : this.model_views.equals(that.model_views));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.mt_date = JdbcWritableBridge.readDate(1, __dbResults);
    this.visitor_id_hash = JdbcWritableBridge.readString(2, __dbResults);
    this.model_id = JdbcWritableBridge.readInteger(3, __dbResults);
    this.model_views = JdbcWritableBridge.readLong(4, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.mt_date = JdbcWritableBridge.readDate(1, __dbResults);
    this.visitor_id_hash = JdbcWritableBridge.readString(2, __dbResults);
    this.model_id = JdbcWritableBridge.readInteger(3, __dbResults);
    this.model_views = JdbcWritableBridge.readLong(4, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeDate(mt_date, 1 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeString(visitor_id_hash, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(model_id, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeLong(model_views, 4 + __off, -5, __dbStmt);
    return 4;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeDate(mt_date, 1 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeString(visitor_id_hash, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(model_id, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeLong(model_views, 4 + __off, -5, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.mt_date = null;
    } else {
    this.mt_date = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.visitor_id_hash = null;
    } else {
    this.visitor_id_hash = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.model_id = null;
    } else {
    this.model_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.model_views = null;
    } else {
    this.model_views = Long.valueOf(__dataIn.readLong());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.mt_date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.mt_date.getTime());
    }
    if (null == this.visitor_id_hash) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, visitor_id_hash);
    }
    if (null == this.model_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.model_id);
    }
    if (null == this.model_views) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.model_views);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.mt_date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.mt_date.getTime());
    }
    if (null == this.visitor_id_hash) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, visitor_id_hash);
    }
    if (null == this.model_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.model_id);
    }
    if (null == this.model_views) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.model_views);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(mt_date==null?"null":"" + mt_date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(visitor_id_hash==null?"null":visitor_id_hash, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(model_id==null?"null":"" + model_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(model_views==null?"null":"" + model_views, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(mt_date==null?"null":"" + mt_date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(visitor_id_hash==null?"null":visitor_id_hash, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(model_id==null?"null":"" + model_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(model_views==null?"null":"" + model_views, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.mt_date = null; } else {
      this.mt_date = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.visitor_id_hash = null; } else {
      this.visitor_id_hash = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.model_id = null; } else {
      this.model_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.model_views = null; } else {
      this.model_views = Long.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.mt_date = null; } else {
      this.mt_date = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.visitor_id_hash = null; } else {
      this.visitor_id_hash = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.model_id = null; } else {
      this.model_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.model_views = null; } else {
      this.model_views = Long.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    visitor_model_view o = (visitor_model_view) super.clone();
    o.mt_date = (o.mt_date != null) ? (java.sql.Date) o.mt_date.clone() : null;
    return o;
  }

  public void clone0(visitor_model_view o) throws CloneNotSupportedException {
    o.mt_date = (o.mt_date != null) ? (java.sql.Date) o.mt_date.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("mt_date", this.mt_date);
    __sqoop$field_map.put("visitor_id_hash", this.visitor_id_hash);
    __sqoop$field_map.put("model_id", this.model_id);
    __sqoop$field_map.put("model_views", this.model_views);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("mt_date", this.mt_date);
    __sqoop$field_map.put("visitor_id_hash", this.visitor_id_hash);
    __sqoop$field_map.put("model_id", this.model_id);
    __sqoop$field_map.put("model_views", this.model_views);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
