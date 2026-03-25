#!/bin/bash

# =========================
# Git Commit Helper Script
# =========================

# Usage:
# ./scripts/git-commit.sh <type> <scope> "<message>"

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
# GET CURRENT BRANCH
# =========================

CURRENT_BRANCH=$(git rev-parse --abbrev-ref HEAD)

if [ -z "$CURRENT_BRANCH" ]; then
  echo "Could not determine current branch"
  exit 1
fi

echo "Current branch: $CURRENT_BRANCH"

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

echo "Pushing to origin/$CURRENT_BRANCH..."
git push -u origin "$CURRENT_BRANCH"

if [ $? -ne 0 ]; then
  echo "Push failed"
  exit 1
fi

echo "Commit and push completed successfully!"