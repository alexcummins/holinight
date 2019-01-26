# api/serializers.py

from rest_framework import serializers
from .models import Party


class PartySerializer(serializers.ModelSerializer):
    """Serializer to map the Model instance into JSON format."""

    class Meta:
        """Meta class to map serializer's fields with the model fields."""
        model = Party
        fields = ('id', 'name', 'Hostname', 'Location', 'date_created', 'date_modified')
        read_only_fields = ('date_created', 'date_modified')
