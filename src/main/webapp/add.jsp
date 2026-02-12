<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Thêm mặt bằng</title>
</head>
<body>

<h2>THÊM MẶT BẰNG</h2>

<% if (request.getAttribute("error") != null) { %>
<p style="color:red;">
    <%= request.getAttribute("error") %>
</p>
<% } %>

<form action="<%=request.getContextPath()%>/matbang" method="post">

    Mã mặt bằng:
    <input type="text" name="maMatBang"
           pattern="MB\d{1}-\d{2}-[A-Z]{2}"
           placeholder="Ví dụ: MB1-02-AB"
           required>
    <br><br>

    Trạng thái:
    <select name="trangThai">
        <option value="Trống">Trống</option>
        <option value="Hạ tầng">Hạ tầng</option>
        <option value="Đầy đủ">Đầy đủ</option>
    </select>
    <br><br>

    Diện tích:
    <input type="number" name="dienTich" min="21" required>
    <br><br>

    Tầng:
    <input type="number" name="tang" min="1" max="15" required>
    <br><br>

    Loại mặt bằng:
    <select name="loaiMatBang">
        <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
        <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
    </select>
    <br><br>

    Giá tiền:
    <input type="number" name="giaTien" min="1000001" required>
    <br><br>

    Ngày bắt đầu:
    <input type="date" name="ngayBatDau" required>
    <br><br>

    Ngày kết thúc:
    <input type="date" name="ngayKetThuc" required>
    <br><br>

    <button type="submit">Thêm</button>

</form>

</body>
</html>
