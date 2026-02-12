package dao;

import model.MatBang;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatBangDAO {

    public List<MatBang> findAll() {
        List<MatBang> list = new ArrayList<>();
        String sql = "select * from mat_bang order by dien_tich asc";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<MatBang> search(String loai, String tangStr, String giaStr) {

        List<MatBang> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from mat_bang where 1=1 ");
        List<Object> params = new ArrayList<>();

        if (loai != null && !loai.isEmpty()) {
            sql.append(" and loai_mat_bang = ? ");
            params.add(loai);
        }

        if (tangStr != null && !tangStr.isEmpty()) {
            sql.append(" and tang = ? ");
            params.add(Integer.parseInt(tangStr));
        }

        if (giaStr != null && !giaStr.isEmpty()) {
            sql.append(" and gia_tien <= ? ");
            params.add(Double.parseDouble(giaStr));
        }

        sql.append(" order by dien_tich asc ");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void insert(MatBang mb) {
        String sql = "insert into mat_bang(ma_mat_bang,trang_thai,dien_tich,tang,loai_mat_bang,gia_tien,ngay_bat_dau,ngay_ket_thuc) values(?,?,?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mb.getMaMatBang());
            ps.setString(2, mb.getTrangThai());
            ps.setDouble(3, mb.getDienTich());
            ps.setInt(4, mb.getTang());
            ps.setString(5, mb.getLoaiMatBang());
            ps.setDouble(6, mb.getGiaTien());
            ps.setDate(7, Date.valueOf(mb.getNgayBatDau()));
            ps.setDate(8, Date.valueOf(mb.getNgayKetThuc()));

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "delete from mat_bang where id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkExist(String ma) {
        String sql = "select * from mat_bang where ma_mat_bang=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private MatBang mapResultSet(ResultSet rs) throws SQLException {
        MatBang mb = new MatBang();
        mb.setId(rs.getInt("id"));
        mb.setMaMatBang(rs.getString("ma_mat_bang"));
        mb.setTrangThai(rs.getString("trang_thai"));
        mb.setDienTich(rs.getDouble("dien_tich"));
        mb.setTang(rs.getInt("tang"));
        mb.setLoaiMatBang(rs.getString("loai_mat_bang"));
        mb.setGiaTien(rs.getDouble("gia_tien"));
        mb.setNgayBatDau(rs.getDate("ngay_bat_dau").toLocalDate());
        mb.setNgayKetThuc(rs.getDate("ngay_ket_thuc").toLocalDate());
        return mb;
    }
}
