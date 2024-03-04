-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 02 mars 2024 à 19:47
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `project`
--

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `idEvent` int(11) NOT NULL,
  `idPartnerCE` int(11) NOT NULL,
  `idEstab` int(11) NOT NULL,
  `nameEvent` varchar(100) NOT NULL,
  `dateEvent` date NOT NULL,
  `nbrMax` int(11) NOT NULL,
  `prix` double NOT NULL,
  `description` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`idEvent`, `idPartnerCE`, `idEstab`, `nameEvent`, `dateEvent`, `nbrMax`, `prix`, `description`, `image`) VALUES
(2, 1, 222, 'HIIIII', '2027-03-16', 10, 100.1, 'HHH', 'src/main/resources/img/event.png'),
(3, 2, 6, 'HELLOOOOO', '2025-02-20', 11, 10000.1, 'F', 'C:\\\\Users\\\\21627\\\\Desktop\\\\Reservation\\\\src\\\\main\\\\resources\\\\img\\\\image1.png');

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `id_res` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `date_P` date NOT NULL DEFAULT current_timestamp(),
  `montant` double NOT NULL,
  `heure_P` varchar(20) NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`id_res`, `id`, `date_P`, `montant`, `heure_P`) VALUES
(191, 112, '2024-03-02', 1.1, '2024-03-02 12:59:37'),
(192, 113, '2024-03-02', 200.2, '2024-03-02 13:04:52'),
(193, 114, '2024-03-02', 1.1, '2024-03-02 13:09:48'),
(194, 115, '2024-03-02', 100.1, '2024-03-02 13:37:11'),
(195, 116, '2024-03-02', 1.1, '2024-03-02 13:42:41'),
(196, 117, '2024-03-02', 1.1, '2024-03-02 13:46:34'),
(197, 118, '2024-03-02', 1.1, '2024-03-02 13:53:56'),
(198, 119, '2024-03-02', 100.1, '2024-03-02 14:30:54'),
(199, 120, '2024-03-02', 300.3, '2024-03-02 14:42:20'),
(200, 121, '2024-03-02', 300.3, '2024-03-02 14:46:36'),
(201, 122, '2024-03-02', 1.1, '2024-03-02 14:54:45'),
(202, 123, '2024-03-02', 30000.3, '2024-03-02 14:57:30'),
(203, 124, '2024-03-02', 10000.1, '2024-03-02 15:05:20');

-- --------------------------------------------------------

--
-- Structure de la table `partner`
--

CREATE TABLE `partner` (
  `idPartner` int(11) NOT NULL,
  `namePartner` varchar(100) NOT NULL,
  `typePartner` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `tel` int(11) NOT NULL,
  `image` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `partner`
--

INSERT INTO `partner` (`idPartner`, `namePartner`, `typePartner`, `description`, `email`, `tel`, `image`) VALUES
(1, 'AA', 'etastablishment', 'HH', 'nousseiba@esprit.tn', 12345678, 'C:\\Users\\21627\\Downloads\\money.png'),
(2, 'ESPRI', 'etastablishment', 'HIII', 'esprit@esprit.tn', 12345679, 'C:\\\\Users\\\\21627\\\\Downloads\\\\log (2).png');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `id_event` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `nb_places` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `imageSrc` varchar(100) NOT NULL,
  `nameE` varchar(100) NOT NULL DEFAULT 'Tanit'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `id_event`, `name`, `email`, `nb_places`, `id_user`, `imageSrc`, `nameE`) VALUES
(191, 3, NULL, NULL, 1, NULL, 'C:\\\\Users\\\\21627\\\\Desktop\\\\Reservation\\\\src\\\\main\\\\resources\\\\img\\\\image1.png', 'HELLOOOOO'),
(192, 2, NULL, NULL, 2, NULL, 'src/main/resources/img/event.png', 'HIIIII'),
(193, 3, NULL, NULL, 1, NULL, 'C:\\\\Users\\\\21627\\\\Desktop\\\\Reservation\\\\src\\\\main\\\\resources\\\\img\\\\image1.png', 'HELLOOOOO'),
(194, 2, NULL, NULL, 1, NULL, 'src/main/resources/img/event.png', 'HIIIII'),
(195, 3, NULL, NULL, 1, NULL, 'C:\\\\Users\\\\21627\\\\Desktop\\\\Reservation\\\\src\\\\main\\\\resources\\\\img\\\\image1.png', 'HELLOOOOO'),
(196, 3, NULL, NULL, 1, NULL, 'C:\\\\Users\\\\21627\\\\Desktop\\\\Reservation\\\\src\\\\main\\\\resources\\\\img\\\\image1.png', 'HELLOOOOO'),
(197, 3, NULL, NULL, 1, NULL, 'C:\\\\Users\\\\21627\\\\Desktop\\\\Reservation\\\\src\\\\main\\\\resources\\\\img\\\\image1.png', 'HELLOOOOO'),
(198, 2, NULL, NULL, 1, NULL, 'src/main/resources/img/event.png', 'HIIIII'),
(199, 2, NULL, NULL, 3, NULL, 'src/main/resources/img/event.png', 'HIIIII'),
(200, 2, NULL, NULL, 3, NULL, 'src/main/resources/img/event.png', 'HIIIII'),
(201, 3, NULL, NULL, 1, NULL, 'C:\\\\Users\\\\21627\\\\Desktop\\\\Reservation\\\\src\\\\main\\\\resources\\\\img\\\\image1.png', 'HELLOOOOO'),
(202, 3, NULL, NULL, 3, NULL, 'C:\\\\Users\\\\21627\\\\Desktop\\\\Reservation\\\\src\\\\main\\\\resources\\\\img\\\\image1.png', 'HELLOOOOO'),
(203, 3, NULL, NULL, 1, NULL, 'C:\\\\Users\\\\21627\\\\Desktop\\\\Reservation\\\\src\\\\main\\\\resources\\\\img\\\\image1.png', 'HELLOOOOO');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `role` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `question` varchar(100) NOT NULL,
  `answer` varchar(100) NOT NULL,
  `Status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `role`, `name`, `email`, `password`, `address`, `question`, `answer`, `Status`) VALUES
(1, 'Apprenant', 'NOUSSA', 'kaabinousseiba11@gmail.com', '123AZE@aze', 'ADD,AA,11', 'What is your favorite color?', 'BLUE', 'Active'),
(2, 'Apprenant', 'aaaa', 'nousseiba@gmail.com', 'AZER123@azer', 'aaa', 'What is the name of your pet?', 'zerty', 'Active'),
(3, 'Tuteur', 'LL', 'AA@AA.AA', 'AZER123@azer', 'AA', 'What is your favorite food?', 'AA', 'Active'),
(4, 'Apprenant', 'AA', 'AA@AA.kk', 'AZER123@azer', 'AA', 'What is your favorite color?', 'AA', 'Active'),
(5, 'Responsable d\'etablissement', 'aa', 'AZER@gmail.com', 'AZER123@azer', 'aa', 'What is the name of your pet?', 'aa', 'Active'),
(6, 'Apprenant', 'eee', 'AZER123@azer.tn', 'AZER123@azer', 'eee', 'What is your favorite food?', 'eee', 'Active'),
(7, 'Tuteur', 'gg', 'AZER@azer.tn', 'AZER123@azer', 'gg', 'What is your favorite color?', 'gg', 'Active'),
(8, 'Apprenant', 'RR', 'mo@mo.tn', 'mo@mo12A', 'RR', 'What is your favorite food?', 'RR', 'Active'),
(9, 'Tuteur', 'ss', 'mo@mo12A.tn', 'mo@mo12A', 'ss', 'What is your favorite food?', 'ss', 'Active');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`idEvent`),
  ADD KEY `fk_partner` (`idPartnerCE`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_res` (`id_res`);

--
-- Index pour la table `partner`
--
ALTER TABLE `partner`
  ADD PRIMARY KEY (`idPartner`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_event` (`id_event`),
  ADD KEY `fk_user` (`id_user`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `idEvent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;

--
-- AUTO_INCREMENT pour la table `partner`
--
ALTER TABLE `partner`
  MODIFY `idPartner` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=204;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `fk_partner` FOREIGN KEY (`idPartnerCE`) REFERENCES `partner` (`idPartner`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `paiement_ibfk_1` FOREIGN KEY (`id_res`) REFERENCES `reservation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_event` FOREIGN KEY (`id_event`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
