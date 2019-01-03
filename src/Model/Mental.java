package Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public  class Mental {
    private final LinkedHashMap<String,SimpleStringProperty> row=new LinkedHashMap();
    protected List<String> colIds=null;

    private Callback< TableColumn.CellDataFeatures<Mental,String>,ObservableValue<String>>
            mapCellValueFactory=new  Callback< TableColumn.CellDataFeatures<Mental,String>,ObservableValue<String>> (){

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Mental, String> param) {
            SimpleStringProperty rtn= param.getValue().getCol(param.getTableColumn().getId());
            return rtn;
        }


    };
    private Mental(){};

    public static Mental create(ArrayList<String> list){

        Mental p=new Mental();
        p.colIds=new ArrayList();
        for(String id:list){
            p.colIds.add(id);
        }
        return p;
    }

    public Mental copyStuct(){
        Mental p=new Mental();
        for (String id:this.colIds){
            p.colIds.add(id);
        }
        return p;
    }

    public Mental  putNewRow(ArrayList<String> datas){
        Mental p=new Mental();
        p.colIds=this.colIds;
        for(int i=0;i<datas.size();i++){
            p.row.put(colIds.get(i), new SimpleStringProperty(datas.get(i)));
        }
        return p;
    }

    public Mental editRow(String... datas){
        for(int i=0;i<datas.length;i++){
            SimpleStringProperty p=row.get(colIds.get(i));
            p.setValue(datas[i]);
        }
        return this;
    }

    public Mental putColData(String col,String data){
        row.put(col, new SimpleStringProperty(data));
        return this;
    }
    public String getColData(int i){
        String id=colIds.get(i);
        return row.get(id).getValue();
    }
    public String getColId(int i){
        String id=colIds.get(i);
        return id;
    }


    public String getColData(String colId){
        return row.get(colId).getValue();
    }
    public SimpleStringProperty getCol(String colId){
        return row.get(colId);
    }

    public LinkedHashMap<String, SimpleStringProperty> getRow() {
        return row;
    }

    public Callback<TableColumn.CellDataFeatures<Mental, String>, ObservableValue<String>> getMapCellValueFactory() {
        return mapCellValueFactory;
    }

    public List<String> getColIds() {
        return colIds;
    }

    @Override
    public String toString() {
        return "Mental{" +
                "row=" + row +
                ", colIds=" + colIds +
                ", mapCellValueFactory=" + mapCellValueFactory +
                '}';
    }
}
