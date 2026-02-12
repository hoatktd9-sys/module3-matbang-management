<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.MatBang" %>

<html>
<head>
    <title>Danh sách mặt bằng</title>
</head>
<body>

<h2>DANH SÁCH MẶT BẰNG</h2>

<a href="matbang?action=add">Thêm mới</a>

<hr>

<h3>TÌM KIẾM</h3>

<form action="matbang" method="get">
    Loại mặt bằng:
    <select name="loaiMatBang">
        <option value="">--Tất cả--</option>
        <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
        <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
    </select>

    Tầng:
    <input type="number" name="tang" min="1" max="15">

    Giá tối đa:
    <input type="number" name="giaTien">

    <button type="submit">Tìm kiếm</button>
</form>

<br>

<table border="1">
    <tr>
        <th>Mã</th>
        <th>Trạng thái</th>
        <th>Diện tích</th>
        <th>Tầng</th>
        <th>Loại</th>
        <th>Giá</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Xóa</th>
    </tr>

    <%
        List<MatBang> list = (List<MatBang>) request.getAttribute("list");
        if (list != null) {
            for (MatBang mb : list) {
    %>

    <tr>
        <td><%= mb.getMaMatBang() %></td>
        <td><%= mb.getTrangThai() %></td>
        <td><%= mb.getDienTich() %></td>
        <td><%= mb.getTang() %></td>
        <td><%= mb.getLoaiMatBang() %></td>
        <td><%= mb.getGiaTien() %></td>
        <td><%= mb.getNgayBatDau() %></td>
        <td><%= mb.getNgayKetThuc() %></td>
        <td>
            <a href="matbang?action=delete&id=<%= mb.getId() %>"
               onclick="return confirm('Bạn có chắc chắn muốn xóa mặt bằng với mã số <%= mb.getMaMatBang() %> không?')">
                Xóa
            </a>
        </td>
    </tr>

    <%      }
    }
    %>

</table>

</body>
</html>
