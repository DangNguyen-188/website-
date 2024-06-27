-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 30, 2022 lúc 10:36 AM
-- Phiên bản máy phục vụ: 10.4.17-MariaDB
-- Phiên bản PHP: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `thietbii`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `iddonhang` int(11) NOT NULL,
  `idsanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `gia` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`iddonhang`, `idsanpham`, `soluong`, `gia`) VALUES
(22, 19, 2, '67980000'),
(25, 19, 2, '67980000'),
(26, 19, 2, '67980000'),
(27, 19, 1, '33990000'),
(28, 14, 1, '33990000'),
(29, 19, 1, '33990000'),
(30, 20, 1, '20500000'),
(31, 20, 1, '20500000'),
(36, 19, 1, '33990000'),
(37, 1, 2, '18000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `email` varchar(250) NOT NULL,
  `diachi` text NOT NULL,
  `sodienthoai` varchar(250) NOT NULL,
  `soluong` int(11) NOT NULL,
  `tongtien` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `iduser`, `email`, `diachi`, `sodienthoai`, `soluong`, `tongtien`) VALUES
(8, 0, '', '', '', 0, ''),
(9, 6, '5 khai tay', '0852156265', 'sonkuht@gmail.com', 3, '127190000'),
(10, 6, '5 khai tay', '0852156265', 'sonkuht@gmail.com', 3, '127190000'),
(19, 6, '5 khai tay', '0852156265', 'sonkuht@gmail.com', 3, '127190000'),
(20, 6, 'fghj', '0852156265', 'sonkuht@gmail.com', 4, '229160000'),
(21, 0, '', '', '', 0, ''),
(22, 6, 'asdas', '0852156265', 'sonkuht@gmail.com', 4, '229160000'),
(24, 0, '', '', '', 0, ''),
(25, 6, 'fggh', '0852156265', 'sonkuht@gmail.com', 3, '182560000'),
(26, 6, 'asdf', '0852156265', 'sonkuht@gmail.com', 3, '182560000'),
(27, 6, 'd', '0852156265', 'sonkuht@gmail.com', 2, '80590000'),
(28, 6, 'ad', '0852156265', 'sonkuht@gmail.com', 1, '33990000'),
(29, 6, 'fffff', '0852156265', 'sonkuht@gmail.com', 1, '33990000'),
(30, 6, 'jjjhg', '0852156265', 'sonkuht@gmail.com', 3, '156460000'),
(31, 6, 'aaaaaaaa', '0852156265', 'sonkuht@gmail.com', 1, '20500000'),
(32, 5, '25 khaitay', '01265265265', 'quyen@gmail.com', 2, '25000'),
(33, 5, '25 khaitay', '01265265265', 'quyen@gmail.com', 2, '25000'),
(34, 5, '25 khaitay', '01265265265', 'quyen@gmail.com', 2, '25000'),
(35, 5, '25 khaitay', '01265265265', 'quyen@gmail.com', 2, '25000'),
(36, 6, 'ad', '0852156265', 'sonkuht@gmail.com', 1, '33990000'),
(37, 9, 'truong', '0123456799', 'quyen12@gmail.com', 2, '36000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(100) NOT NULL,
  `hinhanh` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `hinhanh`) VALUES
(1, 'Trang Chủ', 'https://ngochieu.name.vn/img/home.png'),
(2, 'Điện Thoại', 'https://ngochieu.name.vn/img/mobile.png'),
(3, 'Laptop', 'https://ngochieu.name.vn/img/laptop.png'),
(4, 'Thông Tin', 'https://ngochieu.name.vn/img/info.png'),
(5, 'Liên Hệ', 'https://ngochieu.name.vn/img/contact.png'),
(6, 'Đơn Hàng', 'https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/228863258_221660626528828_8364754500373016519_n.png?_nc_cat=109&ccb=1-6&_nc_sid=09cbfe&_nc_ohc=yP6RZhIKRD0AX-_14MD&_nc_ht=scontent.fdad1-1.fna&oh=00_AT86EbIgSwfxqFLE8A6ZQcHCqX0GGn2-hCNu3iVE601-DQ&oe=628443B3');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanphammoi`
--

CREATE TABLE `sanphammoi` (
  `id` int(11) NOT NULL,
  `tensp` varchar(250) NOT NULL,
  `giasp` varchar(20) NOT NULL,
  `hinhanh` text NOT NULL,
  `mota` text NOT NULL,
  `loai` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanphammoi`
--

INSERT INTO `sanphammoi` (`id`, `tensp`, `giasp`, `hinhanh`, `mota`, `loai`) VALUES
(1, 'Laptop Dell Vostro V3568', '18000000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/dell-V3568-XF6C61-01.jpg', '\"Chip: Intel Core i5-7200U\r\nRAM: DDR4 4GB (2 khe cắm)\r\nỔ cứng: HDD 1TB\r\nChipset đồ họa: Intel HD Graphics 620\r\nMàn hình: 15.6 Inches\r\nHệ điều hành: Free Dos\r\nPin: 4 Cell Lithium-ion\"', 2),
(2, 'Apple Macbook Pro 2017 ', '46000000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/mac-pro-2017-01.jpg', '\"Bộ xử lý: Intel Core i5 dual-core 3.1GHz, Turbo Boost up to 3.5GHz, with 64MB of eDRAM\r\nRAM: 8GB 2133MHz LPDDR3 memory\r\nỔ cứng SSD: 512GB\r\nCard đồ hoạ: Intel Iris Plus Graphics 650\"', 2),
(3, 'Laptop HP Envy 13-ad074TU', '24280000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/hp-envy-13-01.jpg', '\"CPU Intel Core i7-7500U 2.7GHz up to 3.5GHz 4MB\r\nRAM 8GB LPDDR3 Onboard\r\nĐĩa cứng 256 GB PCIe® NVMe™ M.2 SSD\r\nCard đồ họa Intel® HD Graphics 620\r\nMàn hình 13.3 inch FHD (1920 x 1080) diagonal IPS BrightView micro-edge WLED-backlit\"', 2),
(4, 'Điện thoại iPhone 13 Pro', '33990000', 'https://cdn.tgdd.vn/Products/Images/42/230529/iphone-13-pro-max-sierra-blue-600x600.jpg', '\"Kích thước màn hình 6.1 inches\r\nCông nghệ màn hình OLED\r\nCamera sau Camera góc rộng: 12MP, f/1.6\r\nCamera góc siêu rộng: 12MP, ƒ/2.4\r\nCamera trước 12MP, f/2.2\r\nChipset Apple A15\r\nDung lượng RAM 4 GB\"', 1),
(5, 'Samsung Galaxy Note 20 Ultra 5G', '20500000', 'https://www.huang-dan.vn/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.webp', '\"Kích thước màn hình 6.9 inches\r\nCông nghệ màn hình Dynamic AMOLED\r\nCamera sau 108 MP, f/1.8, 26mm (wide), 1/1.33\"\", 0.8µm, PDAF, Laser AF, OIS\r\n12 MP, f/3.0, 103mm (periscope telephoto), 1.0µm, PDAF, OIS, 5x optical zoom, 50x hybrid zoom\r\n12 MP, f/2.2, 13mm (ultrawide), 1/2.55\"\", 1.4µm\r\nCamera trước 10 MP, f/2.2, 26mm (wide), 1/3.2\"\", 1.22µm, Dual Pixel PDAF\r\nChipset Exynos 990 (7 nm+)\r\nDung lượng RAM 12 GB\"', 1),
(6, 'Laptop Dell Vostro V3568', '12200000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/dell-V3568-XF6C61-01.jpg', '\"Chip: Intel Core i5-7200U\r\nRAM: DDR4 4GB (2 khe cắm)\r\nỔ cứng: HDD 1TB\r\nChipset đồ họa: Intel HD Graphics 620\r\nMàn hình: 15.6 Inches\r\nHệ điều hành: Free Dos\r\nPin: 4 Cell Lithium-ion\"', 2),
(7, 'Apple Macbook Pro 2017 ', '46600000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/mac-pro-2017-01.jpg', '\"Bộ xử lý: Intel Core i5 dual-core 3.1GHz, Turbo Boost up to 3.5GHz, with 64MB of eDRAM\r\nRAM: 8GB 2133MHz LPDDR3 memory\r\nỔ cứng SSD: 512GB\r\nCard đồ hoạ: Intel Iris Plus Graphics 650\"', 2),
(8, 'Laptop HP Envy 13-ad074TU', '24280000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/hp-envy-13-01.jpg', '\"CPU Intel Core i7-7500U 2.7GHz up to 3.5GHz 4MB\r\nRAM 8GB LPDDR3 Onboard\r\nĐĩa cứng 256 GB PCIe® NVMe™ M.2 SSD\r\nCard đồ họa Intel® HD Graphics 620\r\nMàn hình 13.3 inch FHD (1920 x 1080) diagonal IPS BrightView micro-edge WLED-backlit\"', 2),
(9, 'Điện thoại iPhone 13 Pro', '33990000', 'https://cdn.tgdd.vn/Products/Images/42/230529/iphone-13-pro-max-sierra-blue-600x600.jpg', '\"Kích thước màn hình 6.1 inches\r\nCông nghệ màn hình OLED\r\nCamera sau Camera góc rộng: 12MP, f/1.6\r\nCamera góc siêu rộng: 12MP, ƒ/2.4\r\nCamera trước 12MP, f/2.2\r\nChipset Apple A15\r\nDung lượng RAM 4 GB\"', 1),
(10, 'Samsung Galaxy Note 20 Ultra 5G', '20500000', 'https://www.huang-dan.vn/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.webp', '\"Kích thước màn hình 6.9 inches\r\nCông nghệ màn hình Dynamic AMOLED\r\nCamera sau 108 MP, f/1.8, 26mm (wide), 1/1.33\"\", 0.8µm, PDAF, Laser AF, OIS\r\n12 MP, f/3.0, 103mm (periscope telephoto), 1.0µm, PDAF, OIS, 5x optical zoom, 50x hybrid zoom\r\n12 MP, f/2.2, 13mm (ultrawide), 1/2.55\"\", 1.4µm\r\nCamera trước 10 MP, f/2.2, 26mm (wide), 1/3.2\"\", 1.22µm, Dual Pixel PDAF\r\nChipset Exynos 990 (7 nm+)\r\nDung lượng RAM 12 GB\"', 1),
(11, 'Laptop Dell Vostro V3568', '12200000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/dell-V3568-XF6C61-01.jpg', '\"Chip: Intel Core i5-7200U\r\nRAM: DDR4 4GB (2 khe cắm)\r\nỔ cứng: HDD 1TB\r\nChipset đồ họa: Intel HD Graphics 620\r\nMàn hình: 15.6 Inches\r\nHệ điều hành: Free Dos\r\nPin: 4 Cell Lithium-ion\"', 2),
(12, 'Apple Macbook Pro 2017 ', '46600000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/mac-pro-2017-01.jpg', '\"Bộ xử lý: Intel Core i5 dual-core 3.1GHz, Turbo Boost up to 3.5GHz, with 64MB of eDRAM\r\nRAM: 8GB 2133MHz LPDDR3 memory\r\nỔ cứng SSD: 512GB\r\nCard đồ hoạ: Intel Iris Plus Graphics 650\"', 2),
(13, 'Laptop HP Envy 13-ad074TU', '24280000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/hp-envy-13-01.jpg', '\"CPU Intel Core i7-7500U 2.7GHz up to 3.5GHz 4MB\r\nRAM 8GB LPDDR3 Onboard\r\nĐĩa cứng 256 GB PCIe® NVMe™ M.2 SSD\r\nCard đồ họa Intel® HD Graphics 620\r\nMàn hình 13.3 inch FHD (1920 x 1080) diagonal IPS BrightView micro-edge WLED-backlit\"', 2),
(14, 'Điện thoại iPhone 13 Pro', '33990000', 'https://cdn.tgdd.vn/Products/Images/42/230529/iphone-13-pro-max-sierra-blue-600x600.jpg', '\"Kích thước màn hình 6.1 inches\r\nCông nghệ màn hình OLED\r\nCamera sau Camera góc rộng: 12MP, f/1.6\r\nCamera góc siêu rộng: 12MP, ƒ/2.4\r\nCamera trước 12MP, f/2.2\r\nChipset Apple A15\r\nDung lượng RAM 4 GB\"', 1),
(15, 'Samsung Galaxy Note 20 Ultra 5G', '20500000', 'https://www.huang-dan.vn/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.webp', '\"Kích thước màn hình 6.9 inches\r\nCông nghệ màn hình Dynamic AMOLED\r\nCamera sau 108 MP, f/1.8, 26mm (wide), 1/1.33\"\", 0.8µm, PDAF, Laser AF, OIS\r\n12 MP, f/3.0, 103mm (periscope telephoto), 1.0µm, PDAF, OIS, 5x optical zoom, 50x hybrid zoom\r\n12 MP, f/2.2, 13mm (ultrawide), 1/2.55\"\", 1.4µm\r\nCamera trước 10 MP, f/2.2, 26mm (wide), 1/3.2\"\", 1.22µm, Dual Pixel PDAF\r\nChipset Exynos 990 (7 nm+)\r\nDung lượng RAM 12 GB\"', 1),
(17, 'Apple Macbook Pro 2017 ', '46600000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/mac-pro-2017-01.jpg', '\"Bộ xử lý: Intel Core i5 dual-core 3.1GHz, Turbo Boost up to 3.5GHz, with 64MB of eDRAM\r\nRAM: 8GB 2133MHz LPDDR3 memory\r\nỔ cứng SSD: 512GB\r\nCard đồ hoạ: Intel Iris Plus Graphics 650\"', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(250) NOT NULL,
  `pass` varchar(250) NOT NULL,
  `username` varchar(100) NOT NULL,
  `mobile` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `pass`, `username`, `mobile`) VALUES
(5, 'quyen123@gmail.com', '123456', 'quyen', '046523845'),
(6, 'sonkuht@gmail.com', '123', 'quyen', '0852156265'),
(7, 'bvquyen.20it3@vku.udn.vn', '123456', 'quyen', '014682515'),
(9, 'quyen12@gmail.com', '123456', 'quyen', '0123456799');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`iddonhang`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
