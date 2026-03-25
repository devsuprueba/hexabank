#!/bin/bash

# =========================
# Git Commit Helper Script
# =========================

# Usage:
# ./scripts/git-commit.sh <type> <scope> "<message>"
#
# Example:
# ./scripts/git-commit.sh feat api "add client endpoints"

TYPE=$1
SCOPE=$2
MESSAGE=$3

# =========================
# VALIDATIONS
# =========================

if [ -z "$TYPE" ] || [ -z "$SCOPE" ] || [ -z "$MESSAGE" ]; then
  echo "Usage: ./scripts/git-commit.sh <type> <scope> \"message\""
  exit 1
fi

# Allowed types
ALLOWED_TYPES=("feat" "fix" "refactor" "chore" "test")

VALID_TYPE=false
for t in "${ALLOWED_TYPES[@]}"; do
  if [ "$TYPE" == "$t" ]; then
    VALID_TYPE=true
    break
  fi
done

if [ "$VALID_TYPE" = false ]; then
  echo "Invalid type: $TYPE"
  echo "Allowed types: feat, fix, refactor, chore, test"
  exit 1
fi

# =========================
# EXECUTION
# =========================

echo "Adding changes..."
git add .

echo "Committing..."
git commit -m "$TYPE($SCOPE): $MESSAGE"

if [ $? -ne 0 ]; then
  echo "Commit failed"
  exit 1
fi

echo "🚀 Pushing to origin/main..."
git push origin main

if [ $? -ne 0 ]; then
  echo "Push failed"
  exit 1
fi

echo "Commit and push completed successfully!"