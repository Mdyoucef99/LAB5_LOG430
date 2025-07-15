#!/bin/bash

echo " Test de Load Balancing - Service Panier"
echo "=========================================="
echo ""

# URL de l'API Gateway
GATEWAY_URL="http://localhost:8080"

# Nombre de requêtes à effectuer
REQUESTS=10

echo " Effectuant $REQUESTS requêtes vers le service panier via l'API Gateway..."
echo ""

# Compteurs pour chaque instance
INSTANCE1_COUNT=0
INSTANCE2_COUNT=0

for i in $(seq 1 $REQUESTS); do
    echo "Requête $i/$REQUESTS..."
    
    # Faire une requête GET vers le service panier
    RESPONSE=$(curl -s -w "\n%{http_code}" "$GATEWAY_URL/carts")
    HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
    BODY=$(echo "$RESPONSE" | head -n -1)
    
    if [ "$HTTP_CODE" = "200" ]; then
        echo " Succès (HTTP $HTTP_CODE)"
    else
        echo " Erreur (HTTP $HTTP_CODE)"
    fi
    
    echo ""
    sleep 0.5
done

echo " Test terminé !"
echo ""
echo " Pour vérifier la répartition de charge :"
echo "   - Consulter les logs: docker-compose -f docker-compose.microservices.yml logs cart-service cart-service-2"
echo "   - Tester directement: curl http://localhost:8085/carts (instance 1)"
echo "   - Tester directement: curl http://localhost:8087/carts (instance 2)" 