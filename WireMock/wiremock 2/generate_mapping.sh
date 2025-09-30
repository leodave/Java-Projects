#!/bin/sh

# Read Ids from csv file
IDS=$(tail -n +2 /ids.csv | paste -sd'|' -)
REGEX="^(${IDS})$"

mkdir -p ./mappings

cat > ./mappings/8.1_generate_automatic_mapping.json <<EOF
{
  "priority": 1,
  "request": {
    "method": "GET",
    "urlPath": "/carts",
    "headers": {
      "search_term": { 
          "matches": "$REGEX"
      }
    }
  },
  "response": {
    "proxyBaseUrl": "https://fakestoreapi.com/"
  }
}
EOF