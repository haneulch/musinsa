INSERT INTO CATEGORY (code, name)
VALUES ('top', '상의'),
       ('outer', '아우터'),
       ('pants', '바지'),
       ('sneakers', '스니커즈'),
       ('bag', '가방'),
       ('hat', '모자'),
       ('socks', '양말'),
       ('accessory', '액세사리');

INSERT INTO BRAND(name)
VALUES ('A'),
       ('B'),
       ('C'),
       ('D'),
       ('E'),
       ('F'),
       ('G'),
       ('H'),
       ('I');

INSERT INTO ITEM(name, brandId, brandName, categoryCode, categoryName, price)
VALUES ('A_top', 1, 'A', 'top', '상의', 11200),
       ('B_top', 2, 'B', 'top', '상의', 10500),
       ('C_top', 3, 'C', 'top', '상의', 10000),
       ('D_top', 4, 'D', 'top', '상의', 10100),
       ('E_top', 5, 'E', 'top', '상의', 10700),
       ('F_top', 6, 'F', 'top', '상의', 11200),
       ('G_top', 7, 'G', 'top', '상의', 10500),
       ('H_top', 8, 'H', 'top', '상의', 10800),
       ('I_top', 9, 'I', 'top', '상의', 11400),

       ('A_outer', 1, 'A', 'outer', '아우터', 5500),
       ('B_outer', 2, 'B', 'outer', '아우터', 5900),
       ('C_outer', 3, 'C', 'outer', '아우터', 6200),
       ('D_outer', 4, 'D', 'outer', '아우터', 5100),
       ('E_outer', 5, 'E', 'outer', '아우터', 5000),
       ('F_outer', 6, 'F', 'outer', '아우터', 7200),
       ('G_outer', 7, 'G', 'outer', '아우터', 5800),
       ('H_outer', 8, 'H', 'outer', '아우터', 6300),
       ('I_outer', 9, 'I', 'outer', '아우터', 6700),

       ('A_pants', 1, 'A', 'pants', '바지', 4200),
       ('B_pants', 2, 'B', 'pants', '바지', 3800),
       ('C_pants', 3, 'C', 'pants', '바지', 3300),
       ('D_pants', 4, 'D', 'pants', '바지', 3000),
       ('E_pants', 5, 'E', 'pants', '바지', 3800),
       ('F_pants', 6, 'F', 'pants', '바지', 4000),
       ('G_pants', 7, 'G', 'pants', '바지', 3900),
       ('H_pants', 8, 'H', 'pants', '바지', 3100),
       ('I_pants', 9, 'I', 'pants', '바지', 3200),

       ('A_sneakers', 1, 'A', 'sneakers', '스니커즈', 9000),
       ('B_sneakers', 2, 'B', 'sneakers', '스니커즈', 9100),
       ('C_sneakers', 3, 'C', 'sneakers', '스니커즈', 9200),
       ('D_sneakers', 4, 'D', 'sneakers', '스니커즈', 9500),
       ('E_sneakers', 5, 'E', 'sneakers', '스니커즈', 9900),
       ('F_sneakers', 6, 'F', 'sneakers', '스니커즈', 9300),
       ('G_sneakers', 7, 'G', 'sneakers', '스니커즈', 9000),
       ('H_sneakers', 8, 'H', 'sneakers', '스니커즈', 9700),
       ('I_sneakers', 9, 'I', 'sneakers', '스니커즈', 9500),

       ('A_bag', 1, 'A', 'bag', '가방', 2000),
       ('B_bag', 2, 'B', 'bag', '가방', 2100),
       ('C_bag', 3, 'C', 'bag', '가방', 2200),
       ('D_bag', 4, 'D', 'bag', '가방', 2500),
       ('E_bag', 5, 'E', 'bag', '가방', 2300),
       ('F_bag', 6, 'F', 'bag', '가방', 2100),
       ('G_bag', 7, 'G', 'bag', '가방', 2200),
       ('H_bag', 8, 'H', 'bag', '가방', 2100),
       ('I_bag', 9, 'I', 'bag', '가방', 2400),

       ('A_hat', 1, 'A', 'hat', '모자', 1700),
       ('B_hat', 2, 'B', 'hat', '모자', 2000),
       ('C_hat', 3, 'C', 'hat', '모자', 1900),
       ('D_hat', 4, 'D', 'hat', '모자', 1500),
       ('E_hat', 5, 'E', 'hat', '모자', 1800),
       ('F_hat', 6, 'F', 'hat', '모자', 1600),
       ('G_hat', 7, 'G', 'hat', '모자', 1700),
       ('H_hat', 8, 'H', 'hat', '모자', 1600),
       ('I_hat', 9, 'I', 'hat', '모자', 1700),

       ('A_socks', 1, 'A', 'socks', '양말', 1800),
       ('B_socks', 2, 'B', 'socks', '양말', 2000),
       ('C_socks', 3, 'C', 'socks', '양말', 2200),
       ('D_socks', 4, 'D', 'socks', '양말', 2400),
       ('E_socks', 5, 'E', 'socks', '양말', 2100),
       ('F_socks', 6, 'F', 'socks', '양말', 2300),
       ('G_socks', 7, 'G', 'socks', '양말', 2100),
       ('H_socks', 8, 'H', 'socks', '양말', 2000),
       ('I_socks', 9, 'I', 'socks', '양말', 1700),

       ('A_accessory', 1, 'A', 'accessory', '액세사리', 2300),
       ('B_accessory', 2, 'B', 'accessory', '액세사리', 2200),
       ('C_accessory', 3, 'C', 'accessory', '액세사리', 2100),
       ('D_accessory', 4, 'D', 'accessory', '액세사리', 2000),
       ('E_accessory', 5, 'E', 'accessory', '액세사리', 2100),
       ('F_accessory', 6, 'F', 'accessory', '액세사리', 1900),
       ('G_accessory', 7, 'G', 'accessory', '액세사리', 2000),
       ('H_accessory', 8, 'H', 'accessory', '액세사리', 2000),
       ('I_accessory', 9, 'I', 'accessory', '액세사리', 2400);