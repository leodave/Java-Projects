#!/bin/bash

# Read profile ID from file
while IFS= read -r profile_id; do
  echo "🔍 Fetching messages for profile: $profile_id"

response=$(curl -H "Authorization: Bearer 6e87d5d7-48a5-4115-b58d-3307b4532ffe" http://beatrix-internal-api-gateway-service.dldx.internal/api/v1/profiles/$profile_id | jq)

 # Append the response (assumed JSON) to the file
echo "$response" >> resultprofile.json
echo "file saved!"
done < profiles.txt