DELETE FROM CATEGORY;
INSERT INTO CATEGORY (code, name)
VALUES ('top', '상의'),
       ( 'outer', '아우터'),
       ( 'pants', '바지'),
       ( 'sneakers', '스니커즈'),
       ( 'bag', '가방'),
       ( 'hat', '모자'),
       ( 'socks', '양말'),
       ( 'accessory', '액세사리');

DELETE FROM BRAND;
INSERT INTO BRAND(id, name)
VALUES (1, 'A'),
       (2, 'B'),
       (3, 'C'),
       (4, 'D'),
       (5, 'E'),
       (6, 'F'),
       (7, 'G'),
       (8, 'H'),
       (9, 'I');

DELETE FROM ITEM;
INSERT INTO ITEM(id, name, brandId, brandName, categoryCode, categoryName, price)
VALUES (1, 'A_top', 1, 'A', 'top', '상의', 11200),
       (2, 'B_top', 2, 'B', 'top', '상의', 10500),
       (3, 'C_top', 3, 'C', 'top', '상의', 10000),
       (4, 'D_top', 4, 'D', 'top', '상의', 10100),
       (5, 'E_top', 5, 'E', 'top', '상의', 10700),
       (6, 'F_top', 6, 'F', 'top', '상의', 11200),
       (7, 'G_top', 7, 'G', 'top', '상의', 10500),
       (8, 'H_top', 8, 'H', 'top', '상의', 10800),
       (9, 'I_top', 9, 'I', 'top', '상의', 11400),

       (11, 'A_outer', 1, 'A', 'outer', '아우터', 5500),
       (12, 'B_outer', 2, 'B', 'outer', '아우터', 5900),
       (13, 'C_outer', 3, 'C', 'outer', '아우터', 6200),
       (14, 'D_outer', 4, 'D', 'outer', '아우터', 5100),
       (15, 'E_outer', 5, 'E', 'outer', '아우터', 5000),
       (16, 'F_outer', 6, 'F', 'outer', '아우터', 7200),
       (17, 'G_outer', 7, 'G', 'outer', '아우터', 5800),
       (18, 'H_outer', 8, 'H', 'outer', '아우터', 6300),
       (19, 'I_outer', 9, 'I', 'outer', '아우터', 6700),

       (21, 'A_pants', 1, 'A', 'pants', '바지', 4200),
       (22, 'B_pants', 2, 'B', 'pants', '바지', 3800),
       (23, 'C_pants', 3, 'C', 'pants', '바지', 3300),
       (24, 'D_pants', 4, 'D', 'pants', '바지', 3000),
       (25, 'E_pants', 5, 'E', 'pants', '바지', 3800),
       (26, 'F_pants', 6, 'F', 'pants', '바지', 4000),
       (27, 'G_pants', 7, 'G', 'pants', '바지', 3900),
       (28, 'H_pants', 8, 'H', 'pants', '바지', 3100),
       (29, 'I_pants', 9, 'I', 'pants', '바지', 3200),

       (31, 'A_sneakers', 1, 'A', 'sneakers', '스니커즈', 9000),
       (32, 'B_sneakers', 2, 'B', 'sneakers', '스니커즈', 9100),
       (33, 'C_sneakers', 3, 'C', 'sneakers', '스니커즈', 9200),
       (34, 'D_sneakers', 4, 'D', 'sneakers', '스니커즈', 9500),
       (35, 'E_sneakers', 5, 'E', 'sneakers', '스니커즈', 9900),
       (36, 'F_sneakers', 6, 'F', 'sneakers', '스니커즈', 9300),
       (37, 'G_sneakers', 7, 'G', 'sneakers', '스니커즈', 9000),
       (38, 'H_sneakers', 8, 'H', 'sneakers', '스니커즈', 9700),
       (39, 'I_sneakers', 9, 'I', 'sneakers', '스니커즈', 9500),

       (41, 'A_bag', 1, 'A', 'bag', '가방', 2000),
       (42, 'B_bag', 2, 'B', 'bag', '가방', 2100),
       (43, 'C_bag', 3, 'C', 'bag', '가방', 2200),
       (44, 'D_bag', 4, 'D', 'bag', '가방', 2500),
       (45, 'E_bag', 5, 'E', 'bag', '가방', 2300),
       (46, 'F_bag', 6, 'F', 'bag', '가방', 2100),
       (47, 'G_bag', 7, 'G', 'bag', '가방', 2200),
       (48, 'H_bag', 8, 'H', 'bag', '가방', 2100),
       (49, 'I_bag', 9, 'I', 'bag', '가방', 2400),

       (51, 'A_hat', 1, 'A', 'hat', '모자', 1700),
       (52, 'B_hat', 2, 'B', 'hat', '모자', 2000),
       (53, 'C_hat', 3, 'C', 'hat', '모자', 1900),
       (54, 'D_hat', 4, 'D', 'hat', '모자', 1500),
       (55, 'E_hat', 5, 'E', 'hat', '모자', 1800),
       (56, 'F_hat', 6, 'F', 'hat', '모자', 1600),
       (57, 'G_hat', 7, 'G', 'hat', '모자', 1700),
       (58, 'H_hat', 8, 'H', 'hat', '모자', 1600),
       (59, 'I_hat', 9, 'I', 'hat', '모자', 1700),

       (61, 'A_socks', 1, 'A', 'socks', '양말', 1800),
       (62, 'B_socks', 2, 'B', 'socks', '양말', 2000),
       (63, 'C_socks', 3, 'C', 'socks', '양말', 2200),
       (64, 'D_socks', 4, 'D', 'socks', '양말', 2400),
       (65, 'E_socks', 5, 'E', 'socks', '양말', 2100),
       (66, 'F_socks', 6, 'F', 'socks', '양말', 2300),
       (67, 'G_socks', 7, 'G', 'socks', '양말', 2100),
       (68, 'H_socks', 8, 'H', 'socks', '양말', 2000),
       (69, 'I_socks', 9, 'I', 'socks', '양말', 1700),

       (71, 'A_accessory', 1, 'A', 'accessory', '액세사리', 2300),
       (72, 'B_accessory', 2, 'B', 'accessory', '액세사리', 2200),
       (73, 'C_accessory', 3, 'C', 'accessory', '액세사리', 2100),
       (74, 'D_accessory', 4, 'D', 'accessory', '액세사리', 2000),
       (75, 'E_accessory', 5, 'E', 'accessory', '액세사리', 2100),
       (76, 'F_accessory', 6, 'F', 'accessory', '액세사리', 1900),
       (77, 'G_accessory', 7, 'G', 'accessory', '액세사리', 2000),
       (78, 'H_accessory', 8, 'H', 'accessory', '액세사리', 2000),
       (79, 'I_accessory', 9, 'I', 'accessory', '액세사리', 2400);