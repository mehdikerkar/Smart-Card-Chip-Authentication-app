# cryptoPuce

C'est quoi CryptoPuce?

CryptoPuce est un programme éducatif qui sert a simuler le fonctionnement des protocoles de cryptographie des cartes à puce, à savoir SDA et DDA. Le projet contient aussi une simulation de l'exploitation d'une faille dans le protocole SDA.
 
 Comment l'utiliser ?

   1. Ouvrirai le projet avec éclipse (ou autre)
   2. Vous trouverez 3 step pour SDA et 2 pour DDA 
    2.1 pour les trois premier (SDA): 
     2.1.1 "step 1 ", c'est là où on va remplir les informations de la carte originale(l'étape est obligatoire au début) Il faut      remplir les informations dans la "Bank" (le champ "Card Number" est remplie automatiquement par la banque) Après appuyé sur l'icône de la banque Vous verriez le défilement des requêtes entre la banque le centre de certification et la carte avec des informations sur l'action qui se passe 
     2.1.2 "step 2" après la première étape nous avons une carte valide dans la deuxième étape en essaye de vérifier l'authenticité de cette dernier Dans le champ "card" choisissez "Originale" Clique sur la carte bancaire Clique sur le terminal et antre le code PIN Clique sur ok et la transaction sera valider 
     2.1.3 "step 3" elle consiste a créer une fausse carte son savoir le pin Clique sur la carte et suivez les évènements Pour tester, il faut revenir sur "step 2" et choisissez "Fake" dans "Card" Pour le test, vous pouvez entrer n'importe quoi dans le pin quand il vous sera demande 
    2.2 les deux dernier sons pour DDA comme l'indique leur nom 
     2.2.1 "DDA Step 1" est identique a "step1"(les mêmes étapes) 
     2.2.2 "DDA Step 2" est identique a "step2"(les mêmes étapes)

System utiliser

OS="Ubuntu 16.04" JAVA_VERSION="1.8.0_131"