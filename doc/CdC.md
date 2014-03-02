# Cahier des charges

## Introduction

Actuellement, nous vivons dans une société plus adaptée aux nouvelles technologies et dans laquelle nous avons de plus en plus besoin de produits intelligents qui nous facilitent la vie.
C’est pour cette raison que nous avons pensé à créer un programme capable de transformer un frigo courant en un frigo qui nous aide à faire les courses habituelles et à gérer les aliments qu’il contient. Cela nous permettrait de ne pas perdre de temps à vérifier l’état des produits de façon régulière ou à faire une liste de courses chaque semaine.

Pour ce faire, nous avons pensé que le frigo devrait contenir un programme informatique qui aura déjà les informations de la plupart des aliments disponibles en supermarché et ses informations correspondantes (la date de péremption, le type d’aliment, la marque…). De cette façon, au moment de ranger nos courses dans le frigo il ne reste plus qu’à lui indiquer les aliments que l’on introduit, afin qu’il puisse identifier et contrôler automatiquement la qualité du produit et sa date de péremption.

Nous allons donc essayer de créer le programme que devrait contenir notre frigo prototype. Ce frigo sera muni d’un ordinateur encastré dans sa porte ainsi qu’un écran avec lequel nous pourrions interagir de façon pratique et confortable. Pour ce faire, nous utiliserons une interface graphique ayant pour but de faciliter et simplifier la communication avec l’utilisateur.
Nous avons pensé créer un menu principal donnant accès à différents différents boutons nous permettant de réaliser les actions suivantes : ajouter ou retirer des aliments, savoir lesquels sont périmés ou encore faire la liste de courses (//Bouton recette?). Chaque bouton mènera une nouvelle fenêtre où nous pourrons réaliser l’action d’une façon simple et graphiquement compréhensible.
Dans la suite, nous allons expliquer précisément ce qui se passe lorsque l’on appuie sur chaque bouton.

## Interactions et Interface Utilisateur

### Menu Principal

![Menu Principal](interface-utilisateur/menuPrincipal.jpg)

Premièrement, et avant d'appuyer sur aucun des buttons, nous trouverons à l’écran l’image d’un frigo avec trois buttons: ajouter, retirer et liste de courses, en plus d’un “triangle alerte”.
Dans l’image nous pouvons observer que les trois buttons seront placés de façon verticale au centre gauche de l’écran et le triangle au dessous à la droite.

Quelque soit le bouton qu’on touche nous avons décidé de créer un effet sur l’écran de façon à que la porte du frigo montré dans le menu principale glisse vers la droite en montrant tout de suite le menu correspondant

### Dialogue d'alerte

Le triangle de couleur rouge indique qu’il y a un ou plusieurs aliments qui ont périmé. Nous pouvons accéder à la liste des aliments périmés en appuyant sur le bouton.

Le triangle sera de couleur jaune si il y en a qui sont proches de leur date de péremption. En appuyant sur le bouton, nous pourrons alors voir la liste des aliment qui périmera dan x jours (possibilité de choisir la durée). Enfin, le triangle vert si tous les produits sont frais.