
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import hibernate.entity.Perkembangancovid19;
import org.hibernate.Query;

import org.hibernate.Session;


public class StatistikController implements Initializable {

    /**
     * Initializes the controller class.
     */
    int updatestate=0;
    @FXML Button simpan_btn;
    @FXML Label terkonfirmasi_lbl;
    @FXML Label dirawat_lbl;
    @FXML Label sembuh_lbl;
    @FXML Label meninggal_lbl;
    @FXML Label plus_terkonfirmasi;
    @FXML Label plus_dirawat;
    @FXML Label plus_sembuh;
    @FXML Label plus_meninggal;
    @FXML private TextField tanggal_tf;
    
    @FXML private TextField terkonfirmasi_tf;
    @FXML private TextField sembuh_tf;
    @FXML private TextField meninggal_tf;
    @FXML private TextField dirawat_tf;
    @FXML private TableView<Perkembangancovid19> perkembangan_tbl;
    @FXML private TableColumn<Perkembangancovid19,String> tanggal_col;
    @FXML private TableColumn<Perkembangancovid19,Integer> terkonfirmasi_col;
    @FXML private TableColumn<Perkembangancovid19,Integer> dirawat_col;
    @FXML private TableColumn<Perkembangancovid19,Integer> meninggal_col;
    @FXML private TableColumn<Perkembangancovid19,Integer> sembuh_col;
    @FXML PieChart perkembangan_pie_chart;
    
    @FXML
    private Pane linechart_pane;
    
    ObservableList<Perkembangancovid19> update = FXCollections.observableArrayList();
    private Object idUpdate;
    
    @FXML
    private void menuDashboard(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/LayarDashboard.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
        myStage.setScene(x);
        myStage.show();
    }
    
    @FXML
    private void statistik(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Statistik.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
        myStage.setScene(x);
        myStage.show();
    }
    
    @FXML
    private void statistikGlobal(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/StatistikGlobal.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
        myStage.setScene(x);
        myStage.show();
    }
    @FXML
    private void menuBiodata(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/Biodata.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
        myStage.setScene(x);
        myStage.show();
    }
    @FXML
    private void menuAbout(ActionEvent event) throws IOException{
        FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/About.fxml"));
        Parent parent = tujuan.load();            
        Scene x = new Scene(parent);            
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
        myStage.setScene(x);
        myStage.show();
    }
    @FXML
    private void logout(ActionEvent event) throws IOException{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("“Apakah Anda hendak logout?”");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk logout");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Alert keluar = new Alert(Alert.AlertType.INFORMATION);             
            keluar.setContentText("Anda memilih logout!");
            keluar.showAndWait();
            
            FXMLLoader tujuan = new FXMLLoader(getClass().getResource("/view/SplashscreenLogin.fxml"));
            Parent parent = tujuan.load();            
            Scene x = new Scene(parent);            
            Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            myStage.setScene(x);
            myStage.show();
            
        } else {
            alert.close();
        }
        
    }
    @FXML
    private void exit(){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("“Apakah Anda hendak keluar dari aplikasi?”");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk keluar tekan Cencel Untuk batal");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Alert keluar = new Alert(Alert.AlertType.INFORMATION); ;            
            keluar.setContentText("Anda memilih keluar!!");
            keluar.showAndWait();
            
            System.exit(0);
        } else {
            alert.close();
        }
    }
    

    
    
    @FXML
    private void simpan(ActionEvent event) throws IOException{
        
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        
        Query query = ss.createQuery("from Perkembangancovid19");
        
        Query queryUpdate = ss.createQuery("UPDATE Perkembangancovid19 SET terkonfirmasi= :terkonfirmasi, dirawat= :dirawat, sembuh = :sembuh,meninggal = :meninggal,waktuPerubahan = :waktuPerubahan where id = :id");

        List list = query.list();
        int id = list.size()+1;
        if(updatestate==0){
            Perkembangancovid19 perkembangan = new Perkembangancovid19();
            perkembangan.setIdperubahan(id);
            perkembangan.setWaktuperubahan(tanggal_tf.getText());
            Integer terkonfirmasi = Integer.parseInt(terkonfirmasi_tf.getText());
            perkembangan.setTerkonfirmasi(terkonfirmasi);
            Integer dirawat = Integer.parseInt(dirawat_tf.getText());
            perkembangan.setDirawat(dirawat);
            Integer sembuh = Integer.parseInt(sembuh_tf.getText());
            perkembangan.setSembuh(sembuh);
            Integer meninggal = Integer.parseInt(meninggal_tf.getText());
            perkembangan.setMeninggal(meninggal);
            ss.save(perkembangan);
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        }else{
                        

            
            Integer terkonfirmasi = Integer.parseInt(terkonfirmasi_tf.getText());

            Integer dirawat = Integer.parseInt(dirawat_tf.getText());

            Integer sembuh = Integer.parseInt(sembuh_tf.getText());

            Integer meninggal = Integer.parseInt(meninggal_tf.getText());



            
            queryUpdate.setParameter("id", idUpdate);
            queryUpdate.setParameter("terkonfirmasi", terkonfirmasi);
            queryUpdate.setParameter("dirawat", dirawat);
            queryUpdate.setParameter("sembuh", sembuh);
            queryUpdate.setParameter("meninggal", meninggal);
            queryUpdate.setParameter("waktuPerubahan", tanggal_tf.getText());
            
            queryUpdate.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        }
        
        
        
        
        ss.getTransaction().commit();
        
        ss.close();
                
        
        statistik(event);
        
    }
    @FXML
    private void edit(ActionEvent event) throws IOException{
        int selected = perkembangan_tbl.getSelectionModel().getSelectedIndex();
        int selected_id = 0;
        if(selected >= 0) {
            if(perkembangan_tbl.getSelectionModel().getSelectedItem() != null) {
                Perkembangancovid19 si = perkembangan_tbl.getSelectionModel().getSelectedItem();
                selected_id = si.getIdperubahan();
            }
            
            Session ss = util.HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            Query query = ss.createQuery("from Perkembangancovid19 where id ="+selected_id);
            List list = query.list();
            for(Object o : list) {
                Perkembangancovid19 per = (Perkembangancovid19)o;
            
                tanggal_tf.setText(per.getWaktuperubahan());
                String konfir = Integer.toString(per.getTerkonfirmasi());
                terkonfirmasi_tf.setText(konfir);
                String rawat = Integer.toString(per.getDirawat());
                dirawat_tf.setText(rawat);
                String sembu = Integer.toString(per.getSembuh());
                sembuh_tf.setText(sembu);
                String ninggal = Integer.toString(per.getMeninggal());
                meninggal_tf.setText(ninggal);
                
            }
            
            ss.getTransaction().commit();
            ss.close();
            updatestate=1;
            idUpdate=selected_id;
            simpan_btn.setText("UPDATE");
            
            
            
        } else {
            JOptionPane.showMessageDialog(null, "Pilih item yang hendak diubah!");
        }
    }
    @FXML
    private void hapus(ActionEvent event) throws IOException{
        
        int selected = perkembangan_tbl.getSelectionModel().getSelectedIndex();
        int selected_id = 0;
        if(selected >= 0) {
            if(perkembangan_tbl.getSelectionModel().getSelectedItem() != null) {
                Perkembangancovid19 si = perkembangan_tbl.getSelectionModel().getSelectedItem();
                selected_id = si.getIdperubahan();
            }
            
            Session ss = util.HibernateUtil.getSessionFactory().openSession();
            ss.beginTransaction();
            Query query = ss.createQuery("delete from Perkembangancovid19 where id = :id");
            query.setParameter("id", selected_id);
            query.executeUpdate();
            
            ss.getTransaction().commit();
            ss.close();
            
            statistik(event);
            
        } else {
            JOptionPane.showMessageDialog(null, "Pilih item yang hendak dihapus !");
        }
    }
    @FXML
    private void reset(ActionEvent event){
        terkonfirmasi_tf.setText("");
        dirawat_tf.setText("");
        sembuh_tf.setText("");
        meninggal_tf.setText("");
    }
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadLineChart();
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  HH:mm");  
        Date sekarang = new Date();
        this.tanggal_tf.setText(dateFormat.format(sekarang));
        
        
               
                
        
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        Query query = ss.createQuery("from Perkembangancovid19");
        
        List list = query.list();
        int terkonfirmasi = 1441;
        int dirawat = 1217;
        int meninggal = 122;
        int sembuh = 75;
        
        
        
        for(Object o : list) {
            Perkembangancovid19 updatelist = (Perkembangancovid19) o;
            
            
            update.add(new Perkembangancovid19(updatelist.getIdperubahan(), updatelist.getWaktuperubahan(), updatelist.getTerkonfirmasi(), updatelist.getDirawat(), updatelist.getSembuh(), updatelist.getMeninggal()));
            meninggal += updatelist.getMeninggal();
            dirawat += updatelist.getDirawat();
            sembuh += updatelist.getSembuh();
            terkonfirmasi += updatelist.getTerkonfirmasi();
            
            

            
            if(updatelist.getIdperubahan()==list.size()){
                String plus1 = Integer.toString(updatelist.getTerkonfirmasi());
                this.plus_terkonfirmasi.setText("+ "+plus1);    
                String plus2 = Integer.toString(updatelist.getDirawat());
                this.plus_dirawat.setText("+ "+plus2);    
                String plus3 = Integer.toString(updatelist.getSembuh());
                this.plus_sembuh.setText("+ "+plus3);    
                String plus4 = Integer.toString(updatelist.getMeninggal());
                this.plus_meninggal.setText("+ "+plus4);    
            }
            
        }
        
       
        //Live Data
        String strDirawat = Integer.toString(dirawat);
        this.dirawat_lbl.setText(strDirawat);    
        String strTerkonfirmasi = Integer.toString(terkonfirmasi);
        this.terkonfirmasi_lbl.setText(strTerkonfirmasi);    
        String strSembuh = Integer.toString(sembuh);
        this.sembuh_lbl.setText(strSembuh);    
        String strMeninggal = Integer.toString(meninggal);
        this.meninggal_lbl.setText(strMeninggal);
        
        
                
        
        
        //Table
        perkembangan_tbl.setItems(update);
        tanggal_col.setCellValueFactory(new PropertyValueFactory<>("waktuperubahan"));
        terkonfirmasi_col.setCellValueFactory(new PropertyValueFactory<>("terkonfirmasi"));
        dirawat_col.setCellValueFactory(new PropertyValueFactory<>("dirawat"));
        meninggal_col.setCellValueFactory(new PropertyValueFactory<>("meninggal"));
        sembuh_col.setCellValueFactory(new PropertyValueFactory<>("sembuh"));
        
        
        
        //Pie Chart
        ObservableList<PieChart.Data> piechart = FXCollections.observableArrayList();
        piechart.addAll(
                new PieChart.Data("Terkonfirmasi",terkonfirmasi),
                new PieChart.Data("Dirawat",dirawat),
                new PieChart.Data("Sembuh",sembuh),
                new PieChart.Data("Meninggal",meninggal)
        );
        this.perkembangan_pie_chart.setData(piechart);
        
        
        
        System.out.println(list.size()+1);
        
        ss.getTransaction().commit();
        ss.close();
    }
    public void loadLineChart(){
        linechart_pane.getChildren().clear();
        Session ss = util.HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        
        Query query = ss.createQuery("from Perkembangancovid19");      
        
        List list = query.list();
        
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Pertambahan");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Perubahan ke-");
        LineChart<String,Number> grafik = new LineChart(xAxis,yAxis);
        grafik.setTitle("Data Perubahan COVID-19");
        
        XYChart.Series lc_terkonfirmasi = new XYChart.Series();
        XYChart.Series lc_dirawat = new XYChart.Series();
        XYChart.Series lc_sembuh = new XYChart.Series();
        XYChart.Series lc_meninggal = new XYChart.Series();
        
        
        lc_terkonfirmasi.setName("Terkonfirmasi");        
        lc_dirawat.setName("Dirawat");        
        lc_sembuh.setName("Sembuh");        
        lc_meninggal.setName("Meninggal");
        for(Object o : list) {
            Perkembangancovid19 updatelist = (Perkembangancovid19) o;
            
            lc_terkonfirmasi.getData().add(new XYChart.Data<>("ke-"+updatelist.getIdperubahan(),updatelist.getTerkonfirmasi()));
            lc_dirawat.getData().add(new XYChart.Data<>("ke-"+updatelist.getIdperubahan(),updatelist.getDirawat()));
            lc_sembuh.getData().add(new XYChart.Data<>("ke-"+updatelist.getIdperubahan(),updatelist.getSembuh()));
            lc_meninggal.getData().add(new XYChart.Data<>("ke-"+updatelist.getIdperubahan(),updatelist.getMeninggal()));            
            

            
            
            
        }
        
        
        grafik.getData().add(lc_meninggal);
        grafik.getData().add(lc_terkonfirmasi);
        grafik.getData().add(lc_dirawat);
        grafik.getData().add(lc_sembuh);
        
        
        grafik.setMaxWidth(447);
        grafik.setMaxHeight(223);
        
        linechart_pane.getChildren().add(grafik);
        
        ss.getTransaction().commit();
        ss.close();
        
    }
    
}
