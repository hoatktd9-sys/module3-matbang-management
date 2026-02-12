package controller;

import dao.MatBangDAO;
import model.MatBang;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/matbang")
public class MatBangServlet extends HttpServlet {

    private MatBangDAO dao = new MatBangDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            req.getRequestDispatcher("add.jsp").forward(req, resp);
            return;
        }

        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/matbang");
            return;
        }


        String loai = req.getParameter("loaiMatBang");
        String tang = req.getParameter("tang");
        String gia = req.getParameter("giaTien");

        List<MatBang> list;

        if ((loai != null && !loai.isEmpty()) ||
                (tang != null && !tang.isEmpty()) ||
                (gia != null && !gia.isEmpty())) {

            list = dao.search(loai, tang, gia);
        } else {
            list = dao.findAll();
        }

        req.setAttribute("list", list);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        try {

            String ma = req.getParameter("maMatBang");
            String trangThai = req.getParameter("trangThai");
            double dienTich = Double.parseDouble(req.getParameter("dienTich"));
            int tang = Integer.parseInt(req.getParameter("tang"));
            String loai = req.getParameter("loaiMatBang");
            double gia = Double.parseDouble(req.getParameter("giaTien"));

            LocalDate ngayBatDau = LocalDate.parse(req.getParameter("ngayBatDau"));
            LocalDate ngayKetThuc = LocalDate.parse(req.getParameter("ngayKetThuc"));

            if (dao.checkExist(ma)) {
                req.setAttribute("error", "Mã mặt bằng vừa thêm đã tồn tại");
                req.getRequestDispatcher("add.jsp").forward(req, resp);
                return;
            }

            if (ngayBatDau.plusMonths(6).isAfter(ngayKetThuc)) {
                req.setAttribute("error", "Ngày kết thúc phải lớn hơn ngày bắt đầu ít nhất 6 tháng");
                req.getRequestDispatcher("add.jsp").forward(req, resp);
                return;
            }

            MatBang mb = new MatBang(ma, trangThai, dienTich, tang, loai, gia, ngayBatDau, ngayKetThuc);
            dao.insert(mb);

            resp.sendRedirect(req.getContextPath() + "/matbang");

        } catch (Exception e) {
            req.setAttribute("error", "Dữ liệu nhập không hợp lệ");
            req.getRequestDispatcher("add.jsp").forward(req, resp);
        }
    }
}
