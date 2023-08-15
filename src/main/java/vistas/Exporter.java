/*
 * CLASE PARA EXPORTAR LA TABLA DEL APARTADO DE ADMINISTRADOR EN UN EXCEL
 */
package vistas;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Jorge
 */
public class Exporter {
      
    private File file;
    private List tabla;
    private List nom_files;
     
    public Exporter(File file, List tabla, List nom_files) throws Exception {
        
    this.file = file;
    this.tabla = tabla;
    this.nom_files = nom_files;
    if(nom_files.size()!=tabla.size()){
        throw new Exception("Error");
    }
  }
    public boolean export() throws WriteException {
    try {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
    WritableWorkbook w = Workbook.createWorkbook(out);
    for (int index = 0; index < tabla.size(); index++) {
      JTable table = (JTable) tabla.get(index);
      WritableSheet s = w.createSheet((String) nom_files.get(index), 0);
      for (int i = 0; i < table.getColumnCount(); i++) {
        String NomCol = table.getColumnName(i);
        s.addCell(new Label(i, 0,NomCol));

        for (int j = 0; j < table.getRowCount(); j++) {
            Object object = table.getValueAt(j, i );
            s.addCell(new Label(i, j + 1, String.valueOf(object))); //lo pone en la fila 0+1
        }
      }
    }
    w.write();
    w.close();
    out.close();
    return true;
    } catch (IOException | WriteException e) {
        System.out.println("Error al exportar a Excel:" + e.getMessage() );
        return false;
    }
   }
}
