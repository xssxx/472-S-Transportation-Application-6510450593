-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table transport_application.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `customer_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `delivered_date` datetime(6) DEFAULT NULL,
  `payment_link` text COLLATE utf8mb4_general_ci,
  `shipment_doc_dir` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `total` double NOT NULL,
  `user_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `worker_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  KEY `FKe1wuebvlqtrkl7ma2li5vnrcp` (`worker_id`),
  CONSTRAINT `FKe1wuebvlqtrkl7ma2li5vnrcp` FOREIGN KEY (`worker_id`) REFERENCES `transportation_worker` (`id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table transport_application.orders: ~4 rows (approximately)
INSERT INTO `orders` (`id`, `customer_address`, `customer_name`, `date`, `delivered_date`, `payment_link`, `shipment_doc_dir`, `status`, `total`, `user_id`, `worker_id`) VALUES
	('06feadb3-4de0-46bb-b1f5-e05ef1d7836f', '911 World Trade Center', 'Pasin Salad', '2025-02-03 01:19:57.702140', NULL, 'https://checkout.stripe.com/c/pay/cs_test_a1m3lqZWguvnYZNXJHFFzIlNi51GGpWiPxMhdJMd4jgBdm52F2uzf6vta9#fidkdWxOYHwnPyd1blpxYHZxWjA0VGowTmExQktqMmNGPWdcUD1Ud2JkXzVPV309ME1sVWRqUXZLf0dEf3BvZjwxSVR9YDxxaDZgU29AcnJzUDxtcXVxPW1xNGBvUl9RM09DNFdLRmcyVUpmNTVzSzBXQTBIZCcpJ2N3amhWYHdzYHcnP3F3cGApJ2lkfGpwcVF8dWAnPyd2bGtiaWBabHFgaCcpJ2BrZGdpYFVpZGZgbWppYWB3dic%2FcXdwYHgl', NULL, 0, 150, 'f0d69852-3596-4b8d-a07b-5417742a31e2', NULL),
	('342e4115-fb27-426f-97d5-f52c9a357898', '911 World Trade Center', 'Pasin Salad', '2025-02-03 01:04:40.960108', NULL, 'https://checkout.stripe.com/c/pay/cs_test_a1d1xcKbeHznkaGa0mXiwkfIx3LhGdDoiYAFWFcRAnTCW3KAu0XMBqOyEN#fidkdWxOYHwnPyd1blpxYHZxWjA0VGowTmExQktqMmNGPWdcUD1Ud2JkXzVPV309ME1sVWRqUXZLf0dEf3BvZjwxSVR9YDxxaDZgU29AcnJzUDxtcXVxPW1xNGBvUl9RM09DNFdLRmcyVUpmNTVzSzBXQTBIZCcpJ2N3amhWYHdzYHcnP3F3cGApJ2lkfGpwcVF8dWAnPyd2bGtiaWBabHFgaCcpJ2BrZGdpYFVpZGZgbWppYWB3dic%2FcXdwYHgl', 'src\\main\\resources\\static\\images\\uploads\\1738588399034_ICSE2024_Code_Suggestion.pdf', 6, 200, 'f0d69852-3596-4b8d-a07b-5417742a31e2', '21eabebf-c080-4572-a187-611fbb2c89ea'),
	('665763ee-824f-4950-924d-f2ce71a79d1b', '911 World Trade Center', 'Koonx', '2025-02-03 19:56:34.641223', NULL, 'https://checkout.stripe.com/c/pay/cs_test_a1US2QDvI017of8Rzru0grmkvvQOyjJPeHFmeNNpAkp2GkzvheAHz1dYOx#fidkdWxOYHwnPyd1blpxYHZxWjA0VGowTmExQktqMmNGPWdcUD1Ud2JkXzVPV309ME1sVWRqUXZLf0dEf3BvZjwxSVR9YDxxaDZgU29AcnJzUDxtcXVxPW1xNGBvUl9RM09DNFdLRmcyVUpmNTVzSzBXQTBIZCcpJ2N3amhWYHdzYHcnP3F3cGApJ2lkfGpwcVF8dWAnPyd2bGtiaWBabHFgaCcpJ2BrZGdpYFVpZGZgbWppYWB3dic%2FcXdwYHgl', NULL, 0, 250, 'f0d69852-3596-4b8d-a07b-5417742a31e2', NULL),
	('e0d13de2-ae61-4197-9bb8-5cb2ad43840e', '911 World Trade Center', 'BANANA', '2025-02-03 20:16:46.262356', NULL, 'https://checkout.stripe.com/c/pay/cs_test_a1kJUbaLXaKojaY48KB9qXxzC2PQ0habtsIhoK3DdMkEA9a0mWs0szrC39#fidkdWxOYHwnPyd1blpxYHZxWjA0VGowTmExQktqMmNGPWdcUD1Ud2JkXzVPV309ME1sVWRqUXZLf0dEf3BvZjwxSVR9YDxxaDZgU29AcnJzUDxtcXVxPW1xNGBvUl9RM09DNFdLRmcyVUpmNTVzSzBXQTBIZCcpJ2N3amhWYHdzYHcnP3F3cGApJ2lkfGpwcVF8dWAnPyd2bGtiaWBabHFgaCcpJ2BrZGdpYFVpZGZgbWppYWB3dic%2FcXdwYHgl', NULL, 4, 150, 'f0d69852-3596-4b8d-a07b-5417742a31e2', '21eabebf-c080-4572-a187-611fbb2c89ea');

-- Dumping structure for table transport_application.order_line
CREATE TABLE IF NOT EXISTS `order_line` (
  `quantity` int NOT NULL,
  `order_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `product_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `FKpf904tci8garypkvm32cqupye` (`product_id`),
  CONSTRAINT `FKk9f9t1tmkbq5w27u8rrjbxxg6` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKpf904tci8garypkvm32cqupye` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table transport_application.order_line: ~2 rows (approximately)
INSERT INTO `order_line` (`quantity`, `order_id`, `product_id`) VALUES
	(1, '06feadb3-4de0-46bb-b1f5-e05ef1d7836f', 'd86a7366-49b2-432a-ab41-1be1f84bd741'),
	(4, '342e4115-fb27-426f-97d5-f52c9a357898', 'c62040b5-3504-481a-81fd-c4f2183bc794'),
	(5, '665763ee-824f-4950-924d-f2ce71a79d1b', '962e670d-d1ab-4373-996f-a18382a23295'),
	(3, 'e0d13de2-ae61-4197-9bb8-5cb2ad43840e', '050ab282-0969-4b77-b5fd-5eb56862f040');

-- Dumping structure for table transport_application.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table transport_application.product: ~2 rows (approximately)
INSERT INTO `product` (`id`, `name`, `type`) VALUES
	('050ab282-0969-4b77-b5fd-5eb56862f040', 'Pod KS', 0),
	('962e670d-d1ab-4373-996f-a18382a23295', 'Pod', 0),
	('c62040b5-3504-481a-81fd-c4f2183bc794', 'Pod', 0),
	('d86a7366-49b2-432a-ab41-1be1f84bd741', 'Pod', 1);

-- Dumping structure for table transport_application.receipt
CREATE TABLE IF NOT EXISTS `receipt` (
  `receipt_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `order_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`receipt_id`),
  UNIQUE KEY `UK50q8oumreljv08ygfygu75kbt` (`order_id`),
  CONSTRAINT `FKo8fi6dx59tstuoahtrp0dpnom` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table transport_application.receipt: ~0 rows (approximately)
INSERT INTO `receipt` (`receipt_id`, `create_at`, `order_id`) VALUES
	('ccff319c-3a2d-47af-977a-aea7d987f4fd', '2025-02-03 01:19:57.702140', '06feadb3-4de0-46bb-b1f5-e05ef1d7836f');

-- Dumping structure for table transport_application.transportation_worker
CREATE TABLE IF NOT EXISTS `transportation_worker` (
  `id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table transport_application.transportation_worker: ~0 rows (approximately)
INSERT INTO `transportation_worker` (`id`, `email`, `name`, `password`, `phone_number`, `status`, `username`) VALUES
	('21eabebf-c080-4572-a187-611fbb2c89ea', 'worker@mail.com', 'woker', '$2a$12$.AwZKf4eSTx9hwZaw/9LOObgNyKZWTbaZmlpmYGzSYNQoZyHpJIE6', '0958730001', 1, 'worker');

-- Dumping structure for table transport_application.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` tinyint DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table transport_application.user: ~2 rows (approximately)
INSERT INTO `user` (`id`, `email`, `name`, `password`, `phone_number`, `role`, `username`) VALUES
	('2f4c4ec3-9cc2-422f-9a07-3867efb670f4', 'admin@example.com', 'admin', '$2a$12$lrfmS0W9di8C11yJTXLSvOa.0o4vBrXHMytryluYOmkQcW.jcEOaq', '0123456789', 0, 'admin'),
	('f0d69852-3596-4b8d-a07b-5417742a31e2', 'user@example.com', 'user', '$2a$12$UmH.kXsVd0nf.uJdonv/Z.yFWhYTiwOdnWGARGDzyJZeFrqo2yhQC', '0958738843', 1, 'user');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
