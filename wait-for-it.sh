#!/bin/sh
# wait-for-it.sh

set -e

host="$1"
shift
cmd="$@"

until sqlplus -L "system/${ORACLE_PWD}@//${host}/XEPDB1" <<EOF
exit;
EOF
do
  >&2 echo "Oracle Database is unavailable - sleeping"
  sleep 1
done

>&2 echo "Oracle Database is up - executing command"
exec $cmd 